package blackwidow.c2.web.rest;

import blackwidow.c2.domain.Agent;
import blackwidow.c2.repository.AgentRepository;
import blackwidow.c2.web.rest.errors.BadRequestAlertException;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link blackwidow.c2.domain.Agent}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AgentResource {

    private final Logger log = LoggerFactory.getLogger(AgentResource.class);

    private final AgentRepository agentRepository;

    public AgentResource(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    /**
     * {@code GET  /agents} : get all the agents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agents in body.
     */
    @GetMapping("/agents")
    public ResponseEntity<List<Agent>> getAllAgents(Pageable pageable) {
        log.debug("REST request to get a page of Agents");
        Page<Agent> page = agentRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /agents/:id} : get the "id" agent.
     *
     * @param id the id of the agent to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the agent, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/agents/{id}")
    public ResponseEntity<Agent> getAgent(@PathVariable Long id) {
        log.debug("REST request to get Agent : {}", id);
        Optional<Agent> agent = agentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(agent);
    }
}
