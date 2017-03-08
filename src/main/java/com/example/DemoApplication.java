package com.example;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public final static String queueName = "messages";
	public final static String exchangeName = "messageExchange";
	
	@Autowired
	RabbitTemplate rabbitTemplate; 
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	Queue queueTilt() {
		return new Queue(queueName, true);
	}
	
	@Bean
	TopicExchange exchangeSensor() {
		return new TopicExchange(exchangeName, true, false);
	}

	@Bean
	Binding bindingRFID(Queue queueTilt, TopicExchange exchangeSensor) {
		return BindingBuilder.bind(queueTilt).to(exchangeSensor).with("messages");
	}
	
	
}
