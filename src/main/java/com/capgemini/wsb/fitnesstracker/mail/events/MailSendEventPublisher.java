package com.capgemini.wsb.fitnesstracker.mail.events;

import com.capgemini.wsb.fitnesstracker.training.events.TrainingEndEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MailSendEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void sendMessageAndPublishEvent(final String message) {
        MailSendEvent mailSendEvent = new MailSendEvent(this, message);
        applicationEventPublisher.publishEvent(mailSendEvent);
    }
}
