package blackwidow.c2.web.rest;

import blackwidow.c2.domain.ArchivedAsyncArtifact;
import blackwidow.c2.repository.ArchivedAsyncArtifactRepository;
import blackwidow.c2.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link blackwidow.c2.domain.ArchivedAsyncArtifact}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ArchivedAsyncArtifactResource {

    private final Logger log = LoggerFactory.getLogger(ArchivedAsyncArtifactResource.class);

    private final ArchivedAsyncArtifactRepository archivedAsyncArtifactRepository;

    public ArchivedAsyncArtifactResource(ArchivedAsyncArtifactRepository archivedAsyncArtifactRepository) {
        this.archivedAsyncArtifactRepository = archivedAsyncArtifactRepository;
    }

    /**
     * {@code GET  /archived-async-artifacts} : get all the archivedAsyncArtifacts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of archivedAsyncArtifacts in body.
     */
    @GetMapping("/archived-async-artifacts")
    public List<ArchivedAsyncArtifact> getAllArchivedAsyncArtifacts() {
        log.debug("REST request to get all ArchivedAsyncArtifacts");
        return archivedAsyncArtifactRepository.findAll();
    }

    /**
     * {@code GET  /archived-async-artifacts/:id} : get the "id" archivedAsyncArtifact.
     *
     * @param id the id of the archivedAsyncArtifact to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the archivedAsyncArtifact, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/archived-async-artifacts/{id}")
    public ResponseEntity<ArchivedAsyncArtifact> getArchivedAsyncArtifact(@PathVariable Long id) {
        log.debug("REST request to get ArchivedAsyncArtifact : {}", id);
        Optional<ArchivedAsyncArtifact> archivedAsyncArtifact = archivedAsyncArtifactRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(archivedAsyncArtifact);
    }
}
