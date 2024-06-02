package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.IEmailSenderService;
import com.capgemini.wsb.fitnesstracker.mail.api.RequestEmailDto;
import com.capgemini.wsb.fitnesstracker.statistics.api.IStatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.IStatisticsService;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.training.api.ITrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public void send(RequestEmailDto email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("springspringtest@outlook.com");
        message.setTo(email.toAddress());
        message.setSubject(email.subject() != null ? email.subject() : "Raport z traningów");
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
        emailSender.send(message);
    }
}
