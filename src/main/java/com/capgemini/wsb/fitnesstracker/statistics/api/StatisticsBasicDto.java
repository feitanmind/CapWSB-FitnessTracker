package com.capgemini.wsb.fitnesstracker.statistics.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatisticsBasicDto {
    private String userName;
    private int totalTrainings;
    private double totalDistance;
    private int totalCaloriesBurned;
}
