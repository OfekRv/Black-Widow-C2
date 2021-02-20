package blackwidow.c2.web.rest;

import blackwidow.c2.domain.CommandType;
import blackwidow.c2.repository.CommandTypeRepository;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link blackwidow.c2.domain.CommandType}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CommandTypeResource {

    private final Logger log = LoggerFactory.getLogger(CommandTypeResource.class);

    private static final String ENTITY_NAME = "commandType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommandTypeRepository commandTypeRepository;

    public CommandTypeResource(CommandTypeRepository commandTypeRepository) {
        this.commandTypeRepository = commandTypeRepository;
    }

    /**
     * {@code POST  /command-types} : Create a new commandType.
     *
     * @param commandType the commandType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commandType, or with status {@code 400 (Bad Request)} if the commandType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/command-types")
    public ResponseEntity<CommandType> createCommandType(@Valid @RequestBody CommandType commandType) throws URISyntaxException {
        log.debug("REST request to save CommandType : {}", commandType);
        if (commandType.getId() != null) {
            throw new BadRequestAlertException("A new commandType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommandType result = commandTypeRepository.save(commandType);
        return ResponseEntity.created(new URI("/api/command-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /command-types} : Updates an existing commandType.
     *
     * @param commandType the commandType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commandType,
     * or with status {@code 400 (Bad Request)} if the commandType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commandType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/command-types")
    public ResponseEntity<CommandType> updateCommandType(@Valid @RequestBody CommandType commandType) throws URISyntaxException {
        log.debug("REST request to update CommandType : {}", commandType);
        if (commandType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommandType result = commandTypeRepository.save(commandType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, commandType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /command-types} : get all the commandTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commandTypes in body.
     */
    @GetMapping("/command-types")
    public ResponseEntity<List<CommandType>> getAllCommandTypes(Pageable pageable) {
        log.debug("REST request to get a page of CommandTypes");
        Page<CommandType> page = commandTypeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /command-types/:id} : get the "id" commandType.
     *
     * @param id the id of the commandType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commandType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/command-types/{id}")
    public ResponseEntity<CommandType> getCommandType(@PathVariable Long id) {
        log.debug("REST request to get CommandType : {}", id);
        Optional<CommandType> commandType = commandTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(commandType);
    }

    /**
     * {@code DELETE  /command-types/:id} : delete the "id" commandType.
     *
     * @param id the id of the commandType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/command-types/{id}")
    public ResponseEntity<Void> deleteCommandType(@PathVariable Long id) {
        log.debug("REST request to delete CommandType : {}", id);
        commandTypeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
