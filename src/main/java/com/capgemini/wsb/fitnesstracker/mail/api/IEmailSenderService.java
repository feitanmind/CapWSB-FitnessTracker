package com.capgemini.wsb.fitnesstracker.mail.api;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotEndedException;

/**
 * API interface for component responsible for sending emails.
 */
public interface IEmailSenderService {

    /**
     * Sends the email message to the recipient from the provided {@link EmailDto}.
     *
     * @param email information on email to be sent
     */
    String send(RequestEmailDto email);

    void sendInformationAboutEndOfTraining(RequestEmailEndTrainingDto dto) throws TrainingNotEndedException;

}
