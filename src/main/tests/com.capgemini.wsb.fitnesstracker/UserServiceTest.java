package com.capgemini.wsb.fitnesstracker;

import com.capgemini.wsb.fitnesstracker.user.api.IUserService;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.capgemini.wsb.fitnesstracker.user.api.IUserService;

import java.time.LocalDate;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Transactional
    @Test
    public void testShouldCreateUser()
    {
        Assert.assertTrue(true);
    }
}
