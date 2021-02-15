package blackwidow.c2.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import blackwidow.c2.domain.Artifact;
import blackwidow.c2.domain.*; // for static metamodels
import blackwidow.c2.repository.ArtifactRepository;
import blackwidow.c2.service.dto.ArtifactCriteria;

/**
 * Service for executing complex queries for {@link Artifact} entities in the database.
 * The main input is a {@link ArtifactCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Artifact} or a {@link Page} of {@link Artifact} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ArtifactQueryService extends QueryService<Artifact> {

    private final Logger log = LoggerFactory.getLogger(ArtifactQueryService.class);

    private final ArtifactRepository artifactRepository;

    public ArtifactQueryService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    /**
     * Return a {@link List} of {@link Artifact} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Artifact> findByCriteria(ArtifactCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Artifact> specification = createSpecification(criteria);
        return artifactRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Artifact} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Artifact> findByCriteria(ArtifactCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Artifact> specification = createSpecification(criteria);
        return artifactRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ArtifactCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Artifact> specification = createSpecification(criteria);
        return artifactRepository.count(specification);
    }

    /**
     * Function to convert {@link ArtifactCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Artifact> createSpecification(ArtifactCriteria criteria) {
        Specification<Artifact> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Artifact_.id));
            }
            if (criteria.getReceiveTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReceiveTime(), Artifact_.receiveTime));
            }
            if (criteria.getContent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContent(), Artifact_.content));
            }
            if (criteria.getAgentId() != null) {
                specification = specification.and(buildSpecification(criteria.getAgentId(),
                    root -> root.join(Artifact_.agent, JoinType.LEFT).get(Agent_.id)));
            }
        }
        return specification;
    }
}
