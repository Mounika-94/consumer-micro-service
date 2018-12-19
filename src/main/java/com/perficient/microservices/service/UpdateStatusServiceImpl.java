package com.perficient.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.perficient.microservices.dao.UpdateStatusDAO;
import com.perficient.microservices.model.Client;

@Service
public class UpdateStatusServiceImpl implements UpdateStatusService {

	@Autowired
	private UpdateStatusDAO updateStatusDAO;

	 @KafkaListener(topics = "Kafka_Example", groupId = "group_json",
	            containerFactory = "clientKafkaListenerFactory")
	    public void consumeJson(Client client) {
	        System.out.println("Consumed JSON Message: " + client.toString());
	        updateStatusDAO.updateStatus(client);
	    }

}
