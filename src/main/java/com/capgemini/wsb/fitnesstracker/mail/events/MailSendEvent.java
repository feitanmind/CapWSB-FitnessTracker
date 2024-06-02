package com.capgemini.wsb.fitnesstracker.mail.events;

import org.springframework.context.ApplicationEvent;

public class MailSendEvent extends ApplicationEvent {
    private String message;
    public MailSendEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
