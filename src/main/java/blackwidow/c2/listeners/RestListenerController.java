package blackwidow.c2.listeners;

import blackwidow.c2.domain.Agent;
import blackwidow.c2.domain.Artifact;
import blackwidow.c2.repository.AgentRepository;
import blackwidow.c2.repository.ArtifactRepository;
import blackwidow.c2.service.dto.IncomingArtifactDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.ZonedDateTime;


@RestController
@RequestMapping("/api")
public class RestListenerController {
    @Inject
    private AgentRepository agentRepository;
    @Inject
    private ArtifactRepository artifactRepository;

    @PostMapping("/submit")
    public void submit(@RequestBody IncomingArtifactDto artifact) {
        String agentIp = artifact.getAgentIp();
        Agent sendingAgent = agentRepository.findByIp(agentIp)
            .orElse(agentRepository.save(new Agent(agentIp, ZonedDateTime.now())));

        artifactRepository.save(new Artifact(ZonedDateTime.now(), artifact.getContent(), sendingAgent));
    }
}
