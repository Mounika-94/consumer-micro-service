package com.perficient.microservices.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;
import com.perficient.microservices.model.Client;

public class UpdateStatusDAOImpl implements UpdateClientStatusDAO {

	@Autowired
	private MongoOperations mongoOperation;

	@Override
	public long updateClientStatus(Client client) {

		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(client.getName()));

		Update update = new Update();
		update.set("status", client.getStatus());

		UpdateResult updatedClientRecord = mongoOperation.upsert(query, update, Client.class);

		System.out.println("Updated client record: " + updatedClientRecord.toString());
		return updatedClientRecord.getModifiedCount();

	}

}
