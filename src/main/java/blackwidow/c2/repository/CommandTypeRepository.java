package blackwidow.c2.repository;

import blackwidow.c2.domain.CommandType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CommandType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommandTypeRepository extends JpaRepository<CommandType, Long> {
}
