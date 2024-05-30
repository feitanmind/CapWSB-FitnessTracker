package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.IStatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.IStatisticsService;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsBasicDto;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
class StatisticsController {

    private final IStatisticsService statisticsService;
    private final IStatisticsProvider statisticsProvider;

    @GetMapping
    public List<StatisticsBasicDto> getBasicsStatistics()
    {
        return statisticsProvider.getAllBasicStatistics();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public StatisticsDto addStatistics(@RequestBody StatisticsDto dto)
    {
        return statisticsService.addStatistics(dto);
    }
    @PutMapping("{statId}")
    public StatisticsDto updateStatistics(@PathVariable("statId") Long statId, @RequestBody StatisticsDto dto)
    {
        StatisticsDto statisticsDto = new StatisticsDto(statId, dto.getUser(),dto.getTotalTrainings(), dto.getTotalDistance(), dto.getTotalCaloriesBurned());
        return statisticsService.updateStatistics(statisticsDto);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteStatistics(@PathVariable("id") Long id)
    {
        statisticsService.deleteStatistics(id);
    }

}
