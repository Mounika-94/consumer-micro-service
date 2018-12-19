package com.perficient.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.perficient.microservices.dao.UpdateStatusDAO;
import com.perficient.microservices.dao.UpdateStatusDAOImpl;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
@ComponentScan({ "com.perficient.microservices" })
@EnableSwagger2
@EnableMongoRepositories(basePackageClasses = UpdateStatusDAO.class)
@EnableDiscoveryClient
public class ConsumerApplication {

	@Bean
	public UpdateStatusDAO updateStatusDAO() {
		return new UpdateStatusDAOImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);

	}

}
