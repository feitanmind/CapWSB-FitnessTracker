package com.capgemini.wsb.fitnesstracker.training.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TrainingEndEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void sendMessageAndPublishEvent(final String message, final Long trainingId) {
        TrainingEndEvent trainingEndEvent = new TrainingEndEvent(this, message, trainingId);
        applicationEventPublisher.publishEvent(trainingEndEvent);
    }
}
