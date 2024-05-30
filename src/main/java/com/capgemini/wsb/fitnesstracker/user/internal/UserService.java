package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserService implements IUserService, IUserProvider {

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

    @Override
    @Transactional
    public void deleteUser(Long userId)
    {
        User user = userRepository.getReferenceById(userId);
        userRepository.delete(user);

    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        if(userDto.Id() == null) throw new UserNotFoundException("Id is required");
        User entityBase = userRepository.getReferenceById(userDto.Id());
        User entity = userMapper.toEntityUpdate(userDto, entityBase);
        userRepository.saveAndFlush(entity);
        return userMapper.toDto(entity);
    }

    @Override
    public Optional<UserDto> getUser(final Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) throw new UserNotFoundException("Can't find user");
        User entity = user.get();
        return Optional.of(userMapper.toDto(entity));
    }

    @Override
    public Optional<UserBasicDto> getUserByEmail(final String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) throw new UserNotFoundException("Can't find user");
        User entity = user.get();
        return Optional.of(userMapper.toBasicDto(entity));
    }

    @Override
    public List<UserBasicDto> findAllUsersWithEmail(String email) {
        return userRepository.findAllUsersWithMail(email).stream().map(userMapper::toBasicDto).toList();
    }


    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }


    @Override
    public List<UserDto> findUsersByAge(int age) {
        return userRepository.findAllUserOlderByAge(age).stream().map(userMapper::toDto).toList();
    }

    @Override
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userRepository.findAll().stream().map(userMapper::toSimpleDto).toList();
    }

    @Override
    public List<UserDto> findAllUsersOlderThanDate(LocalDate time) {
        return userRepository.findAllUsersOlderThanDate(time).stream().map(userMapper::toDto).toList();
    }


}