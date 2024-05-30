package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;
import java.util.Optional;

interface IStatisticsRepository extends JpaRepository<Statistics, Long> {
    public default Optional<Statistics> getStatisticForUser(Long userId)
    {
        return findAll().stream().filter(s -> Objects.equals(s.getUser().getId(), userId)).findFirst();
    }
}
