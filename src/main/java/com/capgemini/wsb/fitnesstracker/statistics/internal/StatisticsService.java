package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.IUserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
class StatisticsService implements IStatisticsProvider, IStatisticsService {

    private final IUserProvider userProvider;
    private final IStatisticsRepository statisticsRepository;

    @Override
    public StatisticsDto getStatistics(Long statisticsId) {
        Statistics statistics = statisticsRepository.getReferenceById(statisticsId);
        return StatisticsMapper.toDto(statistics);
    }

    @Override
    public List<StatisticsBasicDto> getAllBasicStatistics() {
        return  statisticsRepository.findAll().stream().map(StatisticsMapper::toSimpleDto).toList();
    }

    @Override
    public List<StatisticsDto> getListOfAllStatistics() {
        return statisticsRepository.findAll().stream().map(StatisticsMapper::toDto).toList();
    }

    @Override
    public StatisticsDto getStatisticForUser(Long userId) {
        Optional<StatisticsDto> statisticsDto = statisticsRepository.getStatisticForUser(userId).stream().map(StatisticsMapper::toDto).findFirst();
        if(statisticsDto.isPresent()){
            return statisticsDto.get();
        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public StatisticsDto addStatistics(StatisticsDto dto) {
        User user = userProvider.getUserEntity(dto.getUser().Id());
        Statistics statistics = StatisticsMapper.toAddEntity(dto, user);
        statisticsRepository.saveAndFlush(statistics);
        return StatisticsMapper.toDto(statistics);
    }

    @Override
    public StatisticsDto updateStatistics(StatisticsDto dto) {
        User user = userProvider.getUserEntity(dto.getUser().Id());
        Statistics statistics = StatisticsMapper.toEntity(dto, user);
        statisticsRepository.saveAndFlush(statistics);
        return StatisticsMapper.toDto(statistics);
    }

    @Override
    public void deleteStatistics(Long statisticsId) {
        statisticsRepository.deleteById(statisticsId);
    }
}
