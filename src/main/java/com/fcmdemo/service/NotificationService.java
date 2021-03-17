package com.fcmdemo.service;

import org.springframework.stereotype.Service;

import com.fcmdemo.dto.NotificationRequestDTO;
import com.fcmdemo.dto.SubscriptionRequestDTO;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationService {

	private final FirebaseMessaging firebaseMessaging;

	public NotificationService(FirebaseMessaging firebaseMessaging) {
		this.firebaseMessaging = firebaseMessaging;
	}

	public void subscribeToTopic(SubscriptionRequestDTO subscriptionRequestDTO) {
		try {
			firebaseMessaging.subscribeToTopic(subscriptionRequestDTO.getTokens(),
					subscriptionRequestDTO.getTopicName());
		} catch (FirebaseMessagingException e) {
			log.error("Firebase subscribe to topic fail", e);
		}
	}

	public void unsubscribeFromTopic(SubscriptionRequestDTO subscriptionRequestDTO) {
		try {
			firebaseMessaging.unsubscribeFromTopic(subscriptionRequestDTO.getTokens(),
					subscriptionRequestDTO.getTopicName());
		} catch (FirebaseMessagingException e) {
			log.error("Firebase unsubscribe from topic fail", e);
		}
	}

	public String sendPnsToDevice(NotificationRequestDTO notificationRequestDTO) {
		Message message = Message.builder().setToken(notificationRequestDTO.getTarget())
				.setNotification(new Notification(notificationRequestDTO.getTitle(), notificationRequestDTO.getBody()))
				.putData("content", notificationRequestDTO.getTitle()).putData("body", notificationRequestDTO.getBody())
				.build();

		String response = null;
		try {
			response = firebaseMessaging.send(message);
		} catch (FirebaseMessagingException e) {
			log.error("Fail to send firebase notification", e);
		}

		return response;
	}

	public String sendPnsToTopic(NotificationRequestDTO notificationRequestDTO) {
		Message message = Message.builder().setTopic(notificationRequestDTO.getTarget())
				.setNotification(new Notification(notificationRequestDTO.getTitle(), notificationRequestDTO.getBody()))
				.putData("content", notificationRequestDTO.getTitle()).putData("body", notificationRequestDTO.getBody())
				.build();

		String response = null;
		try {
			firebaseMessaging.send(message);
		} catch (FirebaseMessagingException e) {
			log.error("Fail to send firebase notification", e);
		}

		return response;
	}
}
