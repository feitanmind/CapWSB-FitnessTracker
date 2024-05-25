package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.IUserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.IUserService;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
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

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") Long idUser)
    {
        userService.deleteUser(idUser);
        return "Udało się usunąć użytkownika";
    }
}