package com.capgemini.wsb.fitnesstracker.training.events;

import com.capgemini.wsb.fitnesstracker.mail.api.IEmailSenderService;
import com.capgemini.wsb.fitnesstracker.mail.api.RequestEmailEndTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.ITrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotEndedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainingEndEventListener implements ApplicationListener<TrainingEndEvent> {

    @Autowired
    private IEmailSenderService emailSenderService;
    @Autowired
    private ITrainingProvider trainingProvider;

    @Override
    public void onApplicationEvent(TrainingEndEvent event) {
        System.out.println("Training ended" + event.getMessage()+ " Sending message to him");
        List<String> recipents = new ArrayList<>();
        TrainingDto trainingDto = trainingProvider.getTraining(event.getTrainingId());
        recipents.add(trainingDto.getUser().email());
        // tak naprawdę można byłoby tutaj dodać specjalne pole do znajomych użytkowników i dodać maile
        RequestEmailEndTrainingDto requestEmailEndTrainingDto = new RequestEmailEndTrainingDto(recipents, trainingDto.getId(), true); //tu wiadmomo że wyłączyć testow użycie
        try {
            emailSenderService.sendInformationAboutEndOfTraining(requestEmailEndTrainingDto);
        } catch (TrainingNotEndedException e) {
            throw new RuntimeException(e);
        }

    }
}
