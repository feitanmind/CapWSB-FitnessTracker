package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;
import java.util.Optional;

interface IStatisticsRepository extends JpaRepository<Statistics, Long> {
    /**
     * Returns statistics or null for specified user
     * @param userId
     * @return Optional(Statistics)
     */
    public default Optional<Statistics> getStatisticForUser(Long userId)
    {
        return findAll().stream().filter(s -> Objects.equals(s.getUser().getId(), userId)).findFirst();
    }
}
