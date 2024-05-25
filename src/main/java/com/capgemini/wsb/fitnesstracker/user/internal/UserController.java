package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final IUserService userService;
    private final IUserProvider userProvider;


    @GetMapping
    public List<UserDto> getAllUsers() {
        return userProvider.findAllUsers();
    }

    @GetMapping("{id}")
    public UserDto getUser(@PathVariable("id") Long id){
        try{
            return userProvider.getUser(id).get();
        }
        catch(NoSuchElementException ex)
        {
            throw new UserNotFoundException(id);
        }

    }
    @GetMapping("/age/{age}")
    public List<UserDto> getUsersOlderThanAge(@PathVariable("age") int age)
    {
        return  userProvider.findUsersByAge(age);
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
    @PatchMapping
    public UserDto updateUser(@RequestBody UserDto dto)
    {
        return userService.updateUser(dto);
    }

    @GetMapping("/email/{email}")
    public UserSimpleDto getUserByEmail(@PathVariable("email") String email)
    {
        try{
            return userProvider.getUserByEmail(email).get();
        }
        catch(NoSuchElementException ex)
        {
            throw  new UserNotFoundException("Nie ma użytkownika z takim adresem email");
        }
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") Long idUser)
    {
        userService.deleteUser(idUser);
        return "Udało się usunąć użytkownika";
    }
}