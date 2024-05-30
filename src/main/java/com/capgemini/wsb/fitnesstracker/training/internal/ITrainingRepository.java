package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.*;

interface ITrainingRepository extends JpaRepository<Training, Long> {
    /**
     * Find all trainings for user
     * @param userId
     * @return List(Training)
     */
    default List<Training> findAllTrainingsForUser(Long userId)
    {
        return findAll().stream().filter(t -> Objects.equals(t.getUser().getId(), userId)).toList();
    }

    /**
     * Return list of trainings for user with end date before specified date
     * @param date
     * @param userId
     * @return List(Training)
     */
    default List<Training> findTrainingsWithEndDateBeforeSpecifiedEndDate(Date date, Long userId)
    {
        return findAllTrainingsForUser(userId).stream().filter(t -> t.getEndTime().before(date)).toList();
    }
    /**
     * Return list of trainings with end date before specified date for all users
     * @param date
     * @return List(Training)
     */
    default List<Training> findTrainingsWithEndDateBeforeSpecifiedEndDate(Date date)
    {
        return findAll().stream().filter(t -> t.getEndTime().before(date)).toList();
    }

    /**
     * Return list of trainings for user with specified activityTYpe
     * @param activityType
     * @param userId
     * @return List(Training)
     */
    default List<Training> findAllTrainingForSpecifiedActivity(ActivityType activityType, Long userId)
    {
        return findAllTrainingsForUser(userId).stream().filter(t -> t.getActivityType() == activityType).toList();
    }

    /**
     * Return list of trainings for all users with specified activityTYpe
     * @param activityType
     * @return List(Training)
     */
    default List<Training> findAllTrainingForSpecifiedActivity(ActivityType activityType)
    {
        return findAll().stream().filter(t -> t.getActivityType() == activityType).toList();
    }

}
