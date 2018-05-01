package br.com.microservice.dbservice.config;

import com.datastax.driver.core.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCassandraRepositories(basePackages = "br.com.microservice.dbservice.repositories")
public class CassandraConfig extends AbstractCassandraConfiguration {

    public static final String KEYSPACE = "gastoskeyspace";


    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification createKeyspaceSpecification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE);

        return Arrays.asList(createKeyspaceSpecification);
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
    }

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"br.com.microservice.sdbservice"};
    }

    @Bean
    public CommandLineRunner loadData(
            CassandraOperations cassandraTemplate,
            CassandraSessionFactoryBean session) {
        return (args) -> {
            // This prepares the database for the demo and is used to showcase
            // Spring and raw driver level API
            // If you need to get to native Datastacks driver level:
            Session nativeSessionObject = session.getObject();

            /*try {
                ((Session) nativeSessionObject).execute("create table keyspace_gastos.tab_categoria (id uuid PRIMARY KEY, nome text);");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }*/

            /*try {
                ((Session) nativeSessionObject).execute("create TABLE keyspace_gastos.tab_usuario (id uuid PRIMARY KEY, descricao text);");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }*/

            try {
                ((Session) nativeSessionObject).execute("CREATE TABLE gastoskeyspace.tab_gasto (\n" +
                        "    id uuid PRIMARY KEY,\n" +
                        "    categoria bigint,\n" +
                        "    usuario bigint,\n" +
                        "    data timestamp,\n" +
                        "    descricao text,\n" +
                        "    valor decimal );");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            try {
                ((Session) nativeSessionObject).execute("CREATE INDEX IF NOT EXISTS index_data_gasto ON " +
                        "gastoskeyspace.tab_gasto (data);");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            try {
                ((Session) nativeSessionObject).execute("CREATE INDEX IF NOT EXISTS index_descricao_gasto ON " +
                        "gastoskeyspace.tab_gasto (descricao);");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            try {
                ((Session) nativeSessionObject).execute("CREATE INDEX IF NOT EXISTS index_codigousuario_gasto ON " +
                        "gastoskeyspace.tab_gasto (codigousuario);");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };
    }
}
