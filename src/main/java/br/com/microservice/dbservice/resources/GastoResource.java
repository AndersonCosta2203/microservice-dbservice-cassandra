package br.com.microservice.dbservice.resources;

import br.com.microservice.dbservice.domain.GastoEntity;
import br.com.microservice.dbservice.repositories.GastoRepository;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * REST controller for managing GastoEntity.
 */
@RestController
@RequestMapping("/api")
public class GastoResource {
    private final Logger log = LoggerFactory.getLogger(GastoResource.class);

    private static final String ENTITY_NAME = "tab_gasto";

    @Autowired
    private GastoRepository gastoRepository;

    /**
     * POST  /gastos : Create a new gasto.
     *
     * @param gasto the gasto to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gasto,
     *  or with status 400 (Bad Request) if the gasto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gastos")
    // @Timed
    public ResponseEntity<GastoEntity> createGasto(@RequestBody GastoEntity gasto) throws URISyntaxException {
        log.debug("REST request to save GastoEntity : {}", gasto);
        /*if (gasto.getId() != null) {
            throw new BadRequestAlertException("A new gasto cannot already have an ID", ENTITY_NAME, "idexists");
        }*/

        // Para pegar o valor do ID randomico
        gasto.setId(UUID.randomUUID());

        GastoEntity result = gastoRepository.save(gasto);
        return ResponseEntity.created(new URI("/api/gastos/" + result.getId()))
                //.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }



}
