package blackwidow.c2.listeners;

import blackwidow.c2.domain.bl.ArtifactProcessorBl;
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
    private ArtifactProcessorBl bl;

    @PostMapping("/submit")
    public void submit(@RequestBody IncomingArtifactDto artifact) {
        bl.processMessage(artifact, ZonedDateTime.now());
    }
}
