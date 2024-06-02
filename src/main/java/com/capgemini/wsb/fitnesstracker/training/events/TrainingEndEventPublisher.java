package com.capgemini.wsb.fitnesstracker.training.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TrainingEndEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void sendMessageAndPublishEvent(final String message) {
        System.out.println("Publish end of training event");
        TrainingEndEvent trainingEndEvent = new TrainingEndEvent(this, message);
        applicationEventPublisher.publishEvent(trainingEndEvent);
    }
}
