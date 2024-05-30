package com.capgemini.wsb.fitnesstracker.statistics.api;

public interface IStatisticsService {
    StatisticsDto addStatistics(StatisticsDto dto);
    StatisticsDto updateStatistics(StatisticsDto dto);
    void deleteStatistics(Long statisticsId);
}
