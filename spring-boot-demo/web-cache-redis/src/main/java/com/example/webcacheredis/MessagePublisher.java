package com.example.webcacheredis;

public interface MessagePublisher {
    void publish(final String message);
}
