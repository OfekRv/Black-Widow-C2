package blackwidow.c2.repository;

import blackwidow.c2.domain.Artifact;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Artifact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, Long>, JpaSpecificationExecutor<Artifact> {
}
