package com.capgemini.wsb.fitnesstracker.training.events;

import org.springframework.context.ApplicationEvent;

public class TrainingEndEvent extends ApplicationEvent {

    private String message;
    private Long trainingId;

    public TrainingEndEvent(Object source, String message, Long trainingId) {
        super(source);
        this.message = message;
        this.trainingId = trainingId;
    }
    public String getMessage() {
        return message;
    }

    public Long getTrainingId() {
        return trainingId;
    }
}
