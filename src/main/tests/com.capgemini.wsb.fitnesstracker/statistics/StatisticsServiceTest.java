package com.capgemini.wsb.fitnesstracker.statistics;

import com.capgemini.wsb.fitnesstracker.statistics.api.IStatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.IStatisticsService;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsBasicDto;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.user.api.IUserService;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class StatisticsServiceTest {
    @Autowired
    private IStatisticsService statisticsService;
    @Autowired
    private IStatisticsProvider statisticsProvider;
    @Autowired
    private IUserService userService;

    private final  UserDto userDto2 = new UserDto(1L,"Test","Test", LocalDate.of(1990,1,1),"test@test.com");


    @Test
    public void shouldAddStatisticsWhenStatisticsDtoWasPassed()
    {
        //then
        Assert.assertNotNull(statisticsProvider.getAllBasicStatistics());
    }
    @Test
    public void shouldGetAllBasicStatistics()
    {
        UserDto ud = userService.createUser(userDto2);
        StatisticsDto statisticsDto2 = new StatisticsDto(null,ud,1,1.2,200);
        statisticsService.addStatistics(statisticsDto2);
        //given
        UserDto userDto = new UserDto(2L,"Test2","test", LocalDate.of(1990,1,1),"test2@test.com");
        userService.createUser(userDto);
        StatisticsDto statisticsDto = new StatisticsDto(2L,userDto,1,1.2,200);
        statisticsService.addStatistics(statisticsDto);
        final int EXPECTED_NUMBER_OF_ITEMS = 2;
        //when
        List<StatisticsBasicDto> list = statisticsProvider.getAllBasicStatistics();
        //then
        Assert.assertEquals(EXPECTED_NUMBER_OF_ITEMS,list.size());
    }
    @Test
    public void shouldGetFullStatistics()
    {
        UserDto ud = userService.createUser(userDto2);
        StatisticsDto statisticsDto2 = new StatisticsDto(null,ud,1,1.2,200);
        statisticsService.addStatistics(statisticsDto2);
        UserDto ud2 = new UserDto(null,"Test2","test", LocalDate.of(1990,1,1),"test2@test.com");
        UserDto cu2 = userService.createUser(ud2);
        StatisticsDto statisticsDto = new StatisticsDto(null,cu2,1,1.2,200);
        statisticsService.addStatistics(statisticsDto);
        //given

        final int EXPECTED_NUMBER_OF_ITEMS = 2;
        //when
        List<StatisticsDto> list = statisticsProvider.getListOfAllStatistics();
        //then
        Assert.assertEquals(EXPECTED_NUMBER_OF_ITEMS,list.size());
    }
    @Test
    public void shouldGetStatisticsWhenIdWasPassed() {
        UserDto ud = userService.createUser(userDto2);
        StatisticsDto statisticsDto2 = new StatisticsDto(null,ud,1,1.2,200);
        statisticsService.addStatistics(statisticsDto2);
        //given
        final Long id = 1L;
        //when
        StatisticsDto givenStats = statisticsProvider.getStatistics(ud.Id());
        //then
        Assert.assertNotNull(givenStats);

    }
    @Test
    public void shouldReturnStatisticsForUserWhenUserIdWasPassed(){
        UserDto ud = userService.createUser(userDto2);
        StatisticsDto statisticsDto2 = new StatisticsDto(null,ud,1,1.2,200);
        statisticsService.addStatistics(statisticsDto2);
        //given
        final Long userId = 1L;
        final String userFirstName = "Test";
        final String userLastName = "Test";
        //when
        StatisticsDto dto = statisticsProvider.getStatisticForUser(userId);
        //then
        Assert.assertNotNull(dto);
        Assert.assertEquals(userFirstName, dto.getUser().firstName());
        Assert.assertEquals(userLastName, dto.getUser().lastName());

    }
    @Test
    public void shuldUpdateStatisticsWhenDtoWasPassed(){
        UserDto ud = userService.createUser(userDto2);
        StatisticsDto statisticsDto2 = new StatisticsDto(null,ud,1,1.2,200);
        StatisticsDto stdto = statisticsService.addStatistics(statisticsDto2);
        //given
        StatisticsDto std = new StatisticsDto(stdto.getId()+1,ud,2,2,3000);
        //when
        StatisticsDto stdUpdated = statisticsService.updateStatistics(std);
        //then
        Assert.assertEquals(std.getTotalCaloriesBurned(), stdUpdated.getTotalCaloriesBurned());

    }

}
