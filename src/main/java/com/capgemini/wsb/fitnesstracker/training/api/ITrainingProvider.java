package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Optional;

public interface ITrainingProvider {
    /**
     * Return Training when Id was passed
     * @param trainingId
     * @return TrainingDto
     */
    TrainingDto getTraining(Long trainingId);

}
