package blackwidow.c2.service;

import blackwidow.c2.domain.Artifact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Artifact}.
 */
public interface ArtifactService {

    /**
     * Save a artifact.
     *
     * @param artifact the entity to save.
     * @return the persisted entity.
     */
    Artifact save(Artifact artifact);

    /**
     * Get all the artifacts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Artifact> findAll(Pageable pageable);


    /**
     * Get the "id" artifact.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Artifact> findOne(Long id);

    /**
     * Delete the "id" artifact.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
