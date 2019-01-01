package com.example.webcacheredis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class MessageSubscriber implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("Message received: " + new String(message.getBody()));
    }
}
