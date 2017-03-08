package com.example;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample requests:
 * http://localhost:8080/get-all-tilt-switches
 * http://localhost:8080/get-tilt-switches-by-tiltId/need-a-sensor-id
 * http://localhost:8080/get-tilt-events-by-tiltId/need-a-sensor-id
 * http://localhost:8080/get-all-tilt-events-by-tiltId/need-a-sensor-id/between/2014-06-12T00:00:00.000Z/2015-11-12T00:00:00.000Z
 * 
 * @author belle
 *
 */
@RestController
public class TiltSwitchRestAPI {

	@Autowired
	RabbitTemplate rabbitTemplate; 
	
	//http://localhost:8080/get-tilt-switches-by-tiltId/33
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public String get() {
		String msg =  (String) rabbitTemplate.receiveAndConvert(DemoApplication.queueName);
		if (msg != null) {
			return "got " + msg;
		} 
		return "No messages on queue";
	}
	
	//http://localhost:8080/get-all-tilt-switches
	@RequestMapping(value="/publish/{msg}", method=RequestMethod.GET)
	public String pub(@PathVariable String msg) {	
		rabbitTemplate.convertAndSend(DemoApplication.exchangeName, "messages", msg);
		return "Published " + msg;
	}
	



}
