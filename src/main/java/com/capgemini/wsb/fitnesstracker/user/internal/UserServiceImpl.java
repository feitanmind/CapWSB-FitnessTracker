package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.IUserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements IUserService, IUserProvider {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDto createUser(final UserDto user) {
        log.info("Creating User {}", user);
        if (user.Id() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userMapper.toDto(userRepository.saveAndFlush(userMapper.toEntity(user)));
    }
    public int deleteUser(int idUser)
    {
        Long userIdLong = Long.valueOf(idUser);
        var user = userRepository.findById(userIdLong);
        if (user == null) {
            throw new NotFoundException("User Not found");
        }
        userRepository.delete(user.get());
        return 1;
    }
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }



}