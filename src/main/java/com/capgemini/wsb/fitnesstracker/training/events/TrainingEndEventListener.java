package com.capgemini.wsb.fitnesstracker.training.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TrainingEndEventListener implements ApplicationListener<TrainingEndEvent> {
    @Override
    public void onApplicationEvent(TrainingEndEvent event) {
        System.out.println("Training ended" + event.getMessage());
    }
}
