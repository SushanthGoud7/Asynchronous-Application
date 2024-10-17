package com.courierdetails.app.jms;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.courierdetails.app.entity.Courier;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JmsConsumer {

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Listner to consume the published API service message.
	 */
	@SuppressWarnings("unused")
	@JmsListener(destination = "${spring.jms.template.default-destination}", containerFactory = "jmsListenerContainerFactory")
	public void receiveMessage(String jsonCourier) throws JMSException {
		try {
			log.info("Received message: {} " + jsonCourier);
			// Deserialization
			Courier courier = objectMapper.readValue(jsonCourier, Courier.class);
		} catch (Exception e) {
			log.error("Exception occured while receiving the message!!!");
			throw new JMSException("Error processing for received message: " + e.getMessage());
		}
	}
}
