package blackwidow.c2.service.impl;

import blackwidow.c2.service.ArtifactService;
import blackwidow.c2.domain.Artifact;
import blackwidow.c2.repository.ArtifactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Artifact}.
 */
@Service
@Transactional
public class ArtifactServiceImpl implements ArtifactService {

    private final Logger log = LoggerFactory.getLogger(ArtifactServiceImpl.class);

    private final ArtifactRepository artifactRepository;

    public ArtifactServiceImpl(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    @Override
    public Artifact save(Artifact artifact) {
        log.debug("Request to save Artifact : {}", artifact);
        return artifactRepository.save(artifact);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Artifact> findAll(Pageable pageable) {
        log.debug("Request to get all Artifacts");
        return artifactRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Artifact> findOne(Long id) {
        log.debug("Request to get Artifact : {}", id);
        return artifactRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Artifact : {}", id);
        artifactRepository.deleteById(id);
    }
}
