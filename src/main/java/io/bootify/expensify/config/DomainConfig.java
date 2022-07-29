package io.bootify.expensify.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.bootify.expensify.domain")
@EnableJpaRepositories("io.bootify.expensify.repos")
@EnableTransactionManagement
public class DomainConfig {
}
