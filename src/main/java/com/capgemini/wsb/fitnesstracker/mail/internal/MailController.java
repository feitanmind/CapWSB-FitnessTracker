package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.RequestEmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.RequestEmailEndTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotEndedException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/email")
@RequiredArgsConstructor
class MailController {
    private final EmailSenderService emailSenderService;

    @PostMapping
    String sendMail(@RequestBody RequestEmailDto emailDto)
    {
        return emailSenderService.send(emailDto);
    }

    @PostMapping("/end")
    void sendNotificationAboutEndOfTraining(@RequestBody RequestEmailEndTrainingDto endTrainingDto) throws TrainingNotEndedException {
        emailSenderService.sendInformationAboutEndOfTraining(endTrainingDto);
    }
}
