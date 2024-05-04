package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.IUserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @DeleteMapping
    public String deleteUser(@RequestBody Long idUser)
    {
        userService.deleteUser(idUser);
        return "Udało się usunąć użytkownika";
    }
}