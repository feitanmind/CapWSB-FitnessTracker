package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatisticsDto {
    private Long id;
    private UserDto user;
    private int totalTrainings;
    private double totalDistance;
    private int totalCaloriesBurned;
}
