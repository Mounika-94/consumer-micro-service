package com.perficient.microservices.service;

import com.perficient.microservices.model.Client;

public interface UpdateStatusService {
	public void consumeJson(Client client);
}
