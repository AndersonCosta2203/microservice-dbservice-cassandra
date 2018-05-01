package br.com.microservice.dbservice.repositories;

import br.com.microservice.dbservice.domain.GastoEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GastoRepository extends CassandraRepository<GastoEntity, UUID> {
}
