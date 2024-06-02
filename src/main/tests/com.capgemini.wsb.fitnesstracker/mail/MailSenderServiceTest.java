package com.capgemini.wsb.fitnesstracker.mail;

import com.capgemini.wsb.fitnesstracker.mail.api.IEmailSenderService;
import com.capgemini.wsb.fitnesstracker.mail.api.RequestEmailDto;
import com.capgemini.wsb.fitnesstracker.statistics.api.IStatisticsService;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.user.api.IUserService;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class MailSenderServiceTest {

    @Autowired
    private IEmailSenderService emailSenderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IStatisticsService statisticsService;

    UserDto userDto = new UserDto(1L,"Test","Testowy", LocalDate.of(1990,1,1),"testowy@user.com");
    @Test
    public void shouldReturnEmailPropertiesWhenRequestEmailDtoWasPassed()
    {
        UserDto user = userService.createUser(userDto);
        StatisticsDto statisticsDto = new StatisticsDto(null,user,1,1.2,200);
        statisticsService.addStatistics(statisticsDto);
        //given
        RequestEmailDto requestEmailDto = new RequestEmailDto("test@test.com","Test",user.Id(),true);
        //when
        String response = emailSenderService.send(requestEmailDto);
        //then
        boolean returnedContent = response.contains("Email sent");
        Assert.assertTrue(returnedContent);

    }
}
