package gloffice.config.database.mysql;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"gloffice.domain.entity"})
@EnableJpaRepositories(basePackages = {"gloffice.repository"})
@EnableTransactionManagement
public class DefaultDBConfigurationPlain extends JpaDBConfig {

}
