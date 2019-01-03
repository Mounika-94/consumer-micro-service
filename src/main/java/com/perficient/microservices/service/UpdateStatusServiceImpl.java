package com.perficient.microservices.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.perficient.microservices.dao.UpdateClientStatusDAO;
import com.perficient.microservices.model.Client;

@Service
public class UpdateStatusServiceImpl implements UpdateStatusService {

	protected Logger log = LoggerFactory.getLogger(UpdateStatusServiceImpl.class);

	@Autowired
	private UpdateClientStatusDAO updateClientStatusDAO;

	@KafkaListener(topics = "Kafka_Example", groupId = "group_json", containerFactory = "clientKafkaListenerFactory")
	public void consumeJson(Client client) {
		System.out.println("Consumed JSON Message: " + client.toString());
		long updatedClientRecordCount = updateClientStatusDAO.updateClientStatus(client);
		if (updatedClientRecordCount == 1) {
			log.info("Record with Name - '" + client.getName() + "' is updated with Status - '" + client.getStatus()
					+ "'");
		} else {
			log.info("No record found with Name - '" + client.getName() + "'");
		}
	}

}
