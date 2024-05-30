package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;

public class StatisticsMapper {
    public static StatisticsBasicDto toSimpleDto(Statistics statistics)
    {
        return new StatisticsBasicDto(statistics.getUser().getFirstName() + " "+statistics.getUser().getLastName(),statistics.getTotalTrainings(), statistics.getTotalDistance(),statistics.getTotalCaloriesBurned());
    }
    public static Statistics toAddEntity(StatisticsDto dto, User user)
    {
        return new Statistics(user,dto.getTotalTrainings(),dto.getTotalDistance(), dto.getTotalCaloriesBurned());
    }
    public static Statistics toEntity(StatisticsDto dto, User user)
    {
        return new Statistics(dto.getId(),user,dto.getTotalTrainings(),dto.getTotalDistance(), dto.getTotalCaloriesBurned());
    }
    public static StatisticsDto toDto(Statistics entity)
    {
        return new StatisticsDto(entity.getId(), UserMapper.toDto(entity.getUser()), entity.getTotalTrainings(), entity.getTotalDistance(),entity.getTotalCaloriesBurned());
    }
}
