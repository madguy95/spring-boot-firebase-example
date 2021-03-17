package com.fcmdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fcmdemo.dto.NotificationRequestDTO;
import com.fcmdemo.dto.SubscriptionRequestDTO;
import com.fcmdemo.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	/**
	 * subcribe a token to a topic
	 * 
	 * @param subscriptionRequestDTO
	 */
	@PostMapping("/subscribe")
	public void subscribeToTopic(@RequestBody SubscriptionRequestDTO subscriptionRequestDTO) {
		notificationService.subscribeToTopic(subscriptionRequestDTO);
	}

	/**
	 * unsubcribe a token in a topic
	 * 
	 * @param subscriptionRequestDTO
	 */
	@PostMapping("/unsubscribe")
	public void unsubscribeFromTopic(@RequestBody SubscriptionRequestDTO subscriptionRequestDTO) {
		notificationService.unsubscribeFromTopic(subscriptionRequestDTO);
	}

	/**
	 * push notification to a token
	 * 
	 * @param notificationRequestDTO
	 */
	@PostMapping("/token")
	public String sendPnsToDevice(@RequestBody NotificationRequestDTO notificationRequestDTO) {
		return notificationService.sendPnsToDevice(notificationRequestDTO);
	}

	/**
	 * push notification to a topic (all token in topic will receive notif)
	 * 
	 * @param notificationRequestDTO
	 */
	@PostMapping("/topic")
	public String sendPnsToTopic(@RequestBody NotificationRequestDTO notificationRequestDTO) {
		return notificationService.sendPnsToTopic(notificationRequestDTO);
	}
}
