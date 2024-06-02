package com.capgemini.wsb.fitnesstracker.training.events;

import org.springframework.context.ApplicationEvent;

public class TrainingEndEvent extends ApplicationEvent {

    private String message;

    public TrainingEndEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
