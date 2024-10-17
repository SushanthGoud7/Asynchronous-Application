package com.courierdetails.app.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.courierdetails.app.entity.Courier;
import com.courierdetails.app.exceptionhandler.JmsPublishingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JmsPublisher {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${spring.jms.template.default-destination}")
	private String destinationName;

	/**
	 * Publish Method to send the courier message.
	 */
	public void sendCourierMessage(Courier courier) {
		try {
			log.info("Sending Courier Message: " + courier);
			// Serialization
			String jsonCourier = objectMapper.writeValueAsString(courier);
			jmsTemplate.convertAndSend(destinationName, jsonCourier);
		} catch (Exception e) {
			log.error("Error while publishing the message" + courier);
			e.printStackTrace();
			throw new JmsPublishingException(e.getMessage());
		}
	}
}
