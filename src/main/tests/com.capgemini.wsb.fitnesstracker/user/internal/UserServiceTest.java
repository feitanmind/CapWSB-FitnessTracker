package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserProvider userProvider;

    private final UserDto user = new UserDto(null,"Adam","Testowy", LocalDate.of(1990,1,1),"testowy@adam.com");

    @Test
    public void testShouldCreateUserWhenDtoWasPassed()
    {
        //given
        final int numberOfUsers = userProvider.findAllUsers().size();
        final int expectedNumberOfUsers = numberOfUsers + 1;
        //when
        userService.createUser(user);
        //then
        Assert.assertEquals(expectedNumberOfUsers, userProvider.findAllUsers().size());
    }

    @Test
    public void testShouldDeleteUserWhenIdUserWasPassed()
    {
        //given
        final int initialNumberOfUsers = userProvider.findAllUsers().size();
        userService.createUser(user);
        UserDto addedUser = userProvider.findAllUsers().get(0);
        //when
        userService.deleteUser(addedUser.Id());
        int numberUsersAfterDelete = userProvider.findAllUsers().size();
        //then
        Assert.assertEquals(initialNumberOfUsers, numberUsersAfterDelete);
        Assert.assertThrows(UserNotFoundException.class,() -> userProvider.getUser(addedUser.Id()));
    }

    @Test
    public void testShouldUpdateUserWhenUpdatedDtoWasPassed()
    {
        //given
        UserDto dto = userService.createUser(user);
        UserDto dtoUpdated = new UserDto(dto.Id(), dto.firstName(), dto.lastName(), dto.birthdate(),"test@correct.com");
        //when
        userService.updateUser(dtoUpdated);
        //then
        Assert.assertEquals(dtoUpdated.email(),userProvider.getUser(dtoUpdated.Id()).get().email());
    }

    @Test
    public void testShouldGetUserWhenIdWasPassed()
    {
        //given
        UserDto dto = userService.createUser(user);
        //when
        Optional<UserDto> dtoUser = userProvider.getUser(dto.Id());
        //then
        Assert.assertTrue(dtoUser.isPresent());
        Assert.assertEquals(dto.firstName(), dtoUser.get().firstName());
    }

    @Test
    public void testShouldGetUserWhenEmailWasPassed()
    {
        //given
        UserDto dto = userService.createUser(user);
        //when
        Optional<UserBasicDto> dtoUser = userProvider.getUserByEmail(dto.email());
        //then
        Assert.assertTrue(dtoUser.isPresent());
        Assert.assertEquals(dto.email(), dtoUser.get().email());
    }

    @Test
    public void testShouldGetListOfAllUsers(){
        //given
        userService.createUser(user);
        userService.createUser(new UserDto(null,"Test2","Test2",LocalDate.now(),"email2@email.com"));
        userService.createUser(new UserDto(null,"Test3","Test3",LocalDate.now(),"email3@email.com"));
        final int expectedNumberOfUsers = 3;
        //when
        List<UserDto> users = userProvider.findAllUsers();
        //then
        Assert.assertEquals(expectedNumberOfUsers,users.size());
    }

    @Test
    public void testShouldReturnUsersOlderWhenAgeWasPassed()
    {
        //given
        int filterAge = 50;
        userService.createUser(new UserDto(null,"Test2","Test2",LocalDate.of(Year.now().getValue()-filterAge-1,1,1),"email2@email.com"));
        userService.createUser(new UserDto(null,"Test3","Test3",LocalDate.of(Year.now().getValue()-2,1,1),"email3@email.com"));
        int expectedNumberOfUsers = 1;
        //when
        List<UserDto> users = userProvider.findUsersByAge(filterAge);
        //then
        Assert.assertEquals(expectedNumberOfUsers,users.size());
    }
}
