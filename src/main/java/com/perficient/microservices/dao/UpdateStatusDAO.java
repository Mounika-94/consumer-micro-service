package com.perficient.microservices.dao;

import org.springframework.stereotype.Repository;

import com.perficient.microservices.model.Client;

@Repository
public interface UpdateStatusDAO {
	String updateStatus(Client client);
}
