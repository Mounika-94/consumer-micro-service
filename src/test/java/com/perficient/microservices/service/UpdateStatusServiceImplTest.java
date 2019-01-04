package com.perficient.microservices.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.perficient.microservices.dao.UpdateClientStatusDAO;
import com.perficient.microservices.model.Client;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
@ContextConfiguration("/applicationContext.xml")
public class UpdateStatusServiceImplTest {

	@Autowired
	private UpdateClientStatusDAO updateClientStatusDAO;

	@Autowired
	private UpdateStatusServiceImpl updateStatusServiceImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testConsumeJson() {
		Client client = new Client();
		client.setName("Perficient");
		client.setStatus("A");
		updateStatusServiceImpl.consumeJson(client);
	}

}
