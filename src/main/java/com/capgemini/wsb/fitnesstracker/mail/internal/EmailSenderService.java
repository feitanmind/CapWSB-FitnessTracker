package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.*;
import com.capgemini.wsb.fitnesstracker.statistics.api.IStatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.training.api.ITrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotEndedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
class EmailSenderService implements IEmailSenderService {

    @Autowired
    private final JavaMailSender emailSender;

    @Autowired
    private  final IStatisticsProvider statisticsProvider;

    @Autowired
    private  final ITrainingProvider trainingProvider;

    @Override
    public String send(RequestEmailDto email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Fitness Tracker <springspringtest@outlook.com>");
        message.setTo(email.toAddress());
        String subject = email.subject() != null ? email.subject() : "Raport z traningów - na dzień "+ LocalDate.now().toString();
        message.setSubject(subject);
        StatisticsDto statisticsDto = statisticsProvider.getStatisticForUser(email.userId());
        List<TrainingDto> trainingsForUser = trainingProvider.getTrainingsForSpecifiedUser(email.userId());
        List<Long> time = new ArrayList<>();
        trainingsForUser.forEach(t -> time.add(t.getEndTime().getTime()-t.getStartTime().getTime()));
        long sum = time.stream().reduce(0L, Long::sum);
        int seconds = (int) (sum / 1000) % 60 ;
        int minutes = (int) ((sum / (1000*60)) % 60);
        int hours   = (int) ((sum / (1000*60*60)) % 24);

        String content = "Time spend on training: "+hours+" hours "+minutes+" minutes"+ seconds+" second"+" Number of trainings: "+statisticsDto.getTotalTrainings();
        message.setText(content);
        boolean isTest = email.isTest();
        if(!isTest)
        {
            emailSender.send(message);
        }
        String testAnnotation = isTest ? "[Attention! This was a test. Real mail won't send]\n":"";
        return testAnnotation+"Email sent [From: FitnessTracker, To: "+email.toAddress() +"]\n"+
                "Subject: "+subject +"\n"+
                "Content: "+content;
    }

    @Override
    public void sendInformationAboutEndOfTraining(RequestEmailEndTrainingDto dto) throws TrainingNotEndedException {
        TrainingDto trainingDto = trainingProvider.getTraining(dto.trainingId());
        if(!trainingDto.getEndTime().before(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())))
        {
            throw new TrainingNotEndedException();
        }
        String userName = trainingDto.getUser().firstName() + " " + trainingDto.getUser().lastName();
        String recipients = trainingDto.getUser().email() + ";"+String.join(";",dto.recipients());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Fitness Tracker <springspringtest@outlook.com>");
        message.setTo(recipients);
        String subject = "Training for "+userName+" was ended";
        message.setSubject(subject);
        message.setText("Congratulations "+userName+ "!\n Your training was ended! We share this achievement to your friends");
        boolean isTest = dto.isTest();
        if(!isTest)
        {
            emailSender.send(message);
        }
    }
}
