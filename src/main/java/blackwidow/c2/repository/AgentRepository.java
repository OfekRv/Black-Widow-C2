package blackwidow.c2.repository;

import blackwidow.c2.domain.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Spring Data  repository for the Agent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    public Optional<Agent> findByIp(String ip);
}
