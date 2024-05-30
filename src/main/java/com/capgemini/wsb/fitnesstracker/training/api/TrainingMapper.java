package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public static TrainingDto toDto(Training entity)
    {
        return new TrainingDto(entity.getId(),UserMapper.toDto(entity.getUser()),entity.getStartTime(), entity.getEndTime(),entity.getActivityType(),entity.getDistance(),entity.getAverageSpeed());
    }
    public  static Training toEntity(TrainingDto dto, User user)
    {
        return new Training(user,dto.getStartTime(),dto.getEndTime(),dto.getActivityType(),dto.getDistance(), dto.getAverageSpeed());
    }
    public  static Training toUpdateEntity(TrainingDto dto, User user)
    {
        return new Training(dto.getId(),user,dto.getStartTime(),dto.getEndTime(),dto.getActivityType(),dto.getDistance(), dto.getAverageSpeed());
    }
}
