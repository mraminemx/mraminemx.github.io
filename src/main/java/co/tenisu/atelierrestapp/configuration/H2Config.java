package co.tenisu.atelierrestapp.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"co.tenisu.atelierrestapp.repository"})
@EntityScan(basePackages = {"co.tenisu.atelierrestapp.entity"})
public class H2Config {
}
