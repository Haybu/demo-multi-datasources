package io.agilehandy.jdbc;

import io.agilehandy.jdbc.firstDB.domain.FirstEntity;
import io.agilehandy.jdbc.firstDB.repository.FirstRepository;
import io.agilehandy.jdbc.secondDB.domain.SecondEntity;
import io.agilehandy.jdbc.secondDB.repository.SecondRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// reference:
// https://docs.spring.io/spring-boot/docs/current/reference/html/howto-data-access.html#howto-two-datasources

@SpringBootApplication
public class DemoMultiDatasourcesApplication {

	Logger logger = LoggerFactory.getLogger(DemoMultiDatasourcesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoMultiDatasourcesApplication.class, args);
	}


	@Bean
	public CommandLineRunner run(FirstRepository firstRepository, SecondRepository secondRepository) {
		return args -> {
			FirstEntity firstEntity = firstRepository.save(new FirstEntity("my first entity"));
			SecondEntity secondEntity = secondRepository.save(new SecondEntity("my second entity"));

			logger.info(firstEntity.toString());
			logger.info(secondEntity.toString());
		};
	}

}

