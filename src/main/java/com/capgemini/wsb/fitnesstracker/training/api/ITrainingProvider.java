package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.util.Optional;

public interface ITrainingProvider {
    /**
     * Return Training when Id was passed
     * @param trainingId
     * @return TrainingDto
     */
    TrainingDto getTraining(Long trainingId);

    /**
     * Return List of all trainings
     * @return List(TrainingDto)
     */
    List<TrainingDto> getListOfAllTrainings();

    /**
     * Return List of Trainings for specified User
     * @param userId
     * @return List(TrainingDto)
     */
    List<TrainingDto> getTrainingsForSpecifiedUser(Long userId);
}
