package com.perficient.microservices.dao;

import org.springframework.stereotype.Repository;

import com.perficient.microservices.model.Client;

@Repository
public interface UpdateClientStatusDAO {
	long updateClientStatus(Client client);
}
