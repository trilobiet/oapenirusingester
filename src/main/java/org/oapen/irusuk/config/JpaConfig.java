package org.oapen.irusuk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
	basePackages = "org.oapen.irusuk.dataingestion.jpa"
	//, enableDefaultTransactions = false
)
public class JpaConfig {}
