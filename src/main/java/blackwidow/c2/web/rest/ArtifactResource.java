package blackwidow.c2.web.rest;

import blackwidow.c2.domain.Artifact;
import blackwidow.c2.service.ArtifactService;
import blackwidow.c2.web.rest.errors.BadRequestAlertException;
import blackwidow.c2.service.dto.ArtifactCriteria;
import blackwidow.c2.service.ArtifactQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link blackwidow.c2.domain.Artifact}.
 */
@RestController
@RequestMapping("/api")
public class ArtifactResource {

    private final Logger log = LoggerFactory.getLogger(ArtifactResource.class);

    private final ArtifactService artifactService;

    private final ArtifactQueryService artifactQueryService;

    public ArtifactResource(ArtifactService artifactService, ArtifactQueryService artifactQueryService) {
        this.artifactService = artifactService;
        this.artifactQueryService = artifactQueryService;
    }

    /**
     * {@code GET  /artifacts} : get all the artifacts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of artifacts in body.
     */
    @GetMapping("/artifacts")
    public ResponseEntity<List<Artifact>> getAllArtifacts(ArtifactCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Artifacts by criteria: {}", criteria);
        Page<Artifact> page = artifactQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /artifacts/count} : count all the artifacts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/artifacts/count")
    public ResponseEntity<Long> countArtifacts(ArtifactCriteria criteria) {
        log.debug("REST request to count Artifacts by criteria: {}", criteria);
        return ResponseEntity.ok().body(artifactQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /artifacts/:id} : get the "id" artifact.
     *
     * @param id the id of the artifact to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the artifact, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/artifacts/{id}")
    public ResponseEntity<Artifact> getArtifact(@PathVariable Long id) {
        log.debug("REST request to get Artifact : {}", id);
        Optional<Artifact> artifact = artifactService.findOne(id);
        return ResponseUtil.wrapOrNotFound(artifact);
    }
}
