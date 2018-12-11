/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.agilehandy.jdbc.firstDB;

import io.agilehandy.jdbc.datasources.HibernateDialects;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author Haytham Mohamed
 **/
@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "firstEntityManagerFactory",
		transactionManagerRef = "firstTransactionManager",
		basePackages = "io.agilehandy.jdbc.firstDB.repository"
)
@EnableTransactionManagement
public class FirstJpaConfiguration {

	private final DataSourceProperties dataSourceProperties;

	public FirstJpaConfiguration(DataSourceProperties dataSourceProperties) {
		this.dataSourceProperties = dataSourceProperties;
	}

	@Primary
	@Bean(name = "firstEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(final EntityManagerFactoryBuilder builder,
	                                                                        final @Qualifier("first-db") DataSource dataSource) {

		String dialect = HibernateDialects.map(dataSourceProperties.getPlatform());
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		properties.put("hibernate.dialect", dialect);

		return builder
				.dataSource(dataSource)
				.packages("io.agilehandy.jdbc.firstDB.domain")
				.persistenceUnit("firstDb")
				.properties(properties)
				.build();
	}

	@Primary
	@Bean(name = "firstTransactionManager")
	public PlatformTransactionManager firstTransactionManager(@Qualifier("firstEntityManagerFactory")
			                                                          EntityManagerFactory firstEntityManagerFactory) {
		return new JpaTransactionManager(firstEntityManagerFactory);
	}

}
