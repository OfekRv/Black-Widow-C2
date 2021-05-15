package blackwidow.c2.repository;

import blackwidow.c2.domain.ArchivedAsyncArtifact;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ArchivedAsyncArtifact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArchivedAsyncArtifactRepository extends JpaRepository<ArchivedAsyncArtifact, Long> {
    boolean existsByMessageId(String id);
}
