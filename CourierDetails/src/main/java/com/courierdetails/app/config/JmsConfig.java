package com.courierdetails.app.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class JmsConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokeUrl;

	@Value("${spring.activemq.user}")
	private String userName;

	@Value("${spring.activemq.password}")
	private String password;

	@Value("${spring.jms.template.default-destination}")
	private String destinationName;

	// ActiveMQ connection factory bean
	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setTrustAllPackages(true);
		connectionFactory.setBrokerURL(brokeUrl);
		connectionFactory.setUserName(userName);
		connectionFactory.setPassword(password);
		connectionFactory.createContext().close();
		return connectionFactory;
	}

	// JmsTemplate bean
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(destinationName);
		template.setMessageConverter(messageConverter());
		return template;
	}

	// Default message listner connection factory bean
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setMessageConverter(messageConverter());
		factory.setConcurrency("1");
		return factory;
	}

	// Message converter 
	@Bean
	public MessageConverter messageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
