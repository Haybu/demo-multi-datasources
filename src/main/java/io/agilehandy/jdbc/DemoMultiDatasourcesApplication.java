package io.agilehandy.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// reference:
// https://docs.spring.io/spring-boot/docs/current/reference/html/howto-data-access.html#howto-two-datasources

@SpringBootApplication
public class DemoMultiDatasourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMultiDatasourcesApplication.class, args);
	}
}
