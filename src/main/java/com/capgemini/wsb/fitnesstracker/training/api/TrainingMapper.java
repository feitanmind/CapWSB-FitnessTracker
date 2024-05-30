package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public static TrainingDto toDto(Training entity)
    {
        return new TrainingDto(entity.getId(),UserMapper.toDto(entity.getUser()),entity.getStartTime(), entity.getEndTime(),entity.getActivityType(),entity.getDistance(),entity.getAverageSpeed());
    }
}
