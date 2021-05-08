package blackwidow.c2.domain.bl;

import blackwidow.c2.domain.ArchivedAsyncArtifact;
import blackwidow.c2.repository.ArchivedAsyncArtifactRepository;
import blackwidow.c2.service.dto.IncomingArtifactDto;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@Named
public class AsyncArtifactListenerBl {
    @Inject
    private ArchivedAsyncArtifactRepository repository;
    @Inject
    private ArtifactProcessorBl processorBl;

    @Transactional
    public void processMessage(String id, IncomingArtifactDto artifact) {
        if (!repository.existsByMessageId(id)) {
            processorBl.processMessage(artifact);
            repository.save(new ArchivedAsyncArtifact(id, ZonedDateTime.now()));
        }
    }
}
