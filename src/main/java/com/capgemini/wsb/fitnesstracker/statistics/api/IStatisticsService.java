package com.capgemini.wsb.fitnesstracker.statistics.api;

public interface IStatisticsService {
    /**
     * Add new Statics
     * @param dto
     * @return StaticsDto
     */
    StatisticsDto addStatistics(StatisticsDto dto);

    /**
     * Update Statistics
     * @param dto
     * @return StatisticsDto
     */
    StatisticsDto updateStatistics(StatisticsDto dto);

    /**
     * Delete statistics
     * @param statisticsId
     */
    void deleteStatistics(Long statisticsId);
}
