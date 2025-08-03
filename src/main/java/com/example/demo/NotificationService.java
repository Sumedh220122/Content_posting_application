package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendMessage(int recipientId, String from, String to, String message) {
        Notification notification = Notification.getInstance2(recipientId, from, to, message);
        notification.send_message(notification);
    }

    public List<List<String>> getMessages(int id) {
        Notification notification = Notification.getInstance();
        return notification.getmessages(id);
    }
}
