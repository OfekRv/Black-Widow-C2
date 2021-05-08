package blackwidow.c2.domain.bl;

import blackwidow.c2.domain.Agent;
import blackwidow.c2.domain.Artifact;
import blackwidow.c2.repository.AgentRepository;
import blackwidow.c2.repository.ArtifactRepository;
import blackwidow.c2.service.dto.IncomingArtifactDto;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.ZonedDateTime;

@Named
public class ArtifactProcessorBl {
    @Inject
    private AgentRepository agentRepository;
    @Inject
    private ArtifactRepository artifactRepository;

    public void processMessage(IncomingArtifactDto artifact) {
        String agentIp = artifact.getAgentIp();
        Agent sendingAgent = agentRepository.findByIp(agentIp)
            .orElseGet(() -> agentRepository.save(new Agent(agentIp, ZonedDateTime.now())));

        artifactRepository.save(new Artifact(ZonedDateTime.now(), artifact.getContent(), sendingAgent));

        sendingAgent.setLastActive(ZonedDateTime.now());
        agentRepository.save(sendingAgent);
    }
}
