package com.perficient.microservices.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.perficient.microservices.model.Client;

@EnableKafka
@Configuration

public class KafkaConsumerConfig {
	
	@ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
	@Bean
	public ConsumerFactory<String, Client> clientConsumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new JsonDeserializer<>(Client.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Client> clientKafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Client> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(clientConsumerFactory());
		return factory;
	}

}
