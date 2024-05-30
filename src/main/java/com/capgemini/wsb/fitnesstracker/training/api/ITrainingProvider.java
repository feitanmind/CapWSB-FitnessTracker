package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Date;
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

    /**
     * Return List of Training FOR USER with End Date before Date
     * @param date
     * @param userId
     * @return List(TrainingDto)
     */
    List<TrainingDto> getListOfTrainingForUserWithEndDateBeforeDate(Date date, Long userId);

    /**
     * Return List of Training FOR ALL USERS with End Date before Date
     * @param date
     * @return List(TrainingDto)
     */
    List<TrainingDto> getListOfTrainingForAllUsersWithEndDateBeforeDate(Date date);

    /**
     * Return List of Training FOR USER with specified Activity
     * @param activityType
     * @param userId
     * @return List(TrainingDto)
     */
    List<TrainingDto> getListOfTrainingForUserWithSpecifiedActivity(ActivityType activityType, Long userId);

    /**
     * Return List of Training FOR ALL USERS with specified Activity
     * @param activityType
     * @return List(TrainingDto)
     */
    List<TrainingDto> getListOfTrainingForAllUsersWithSpecifiedActivity(ActivityType activityType);

}
