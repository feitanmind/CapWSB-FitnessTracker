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
@Transactional
@Slf4j
class UserService implements IUserService, IUserProvider {

    private final IUserRepository userRepository;
    @Override
    public UserDto createUser(final UserDto user) {
        log.info("Creating User {}", user);
        if (user.Id() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return UserMapper.toDto(userRepository.saveAndFlush(UserMapper.toEntity(user)));
    }

    @Override
    public void deleteUser(Long userId)
    {
        User user = userRepository.getReferenceById(userId);
        userRepository.delete(user);

    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        if(userDto.Id() == null) throw new UserNotFoundException("Id is required");
        User entityBase = userRepository.getReferenceById(userDto.Id());
        User entity = UserMapper.toEntityUpdate(userDto, entityBase);
        userRepository.saveAndFlush(entity);
        return UserMapper.toDto(entity);
    }

    @Override
    public Optional<UserDto> getUser(final Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) throw new UserNotFoundException("Can't find user");
        User entity = user.get();
        return Optional.of(UserMapper.toDto(entity));
    }

    @Override
    public Optional<UserBasicDto> getUserByEmail(final String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) throw new UserNotFoundException("Can't find user");
        User entity = user.get();
        return Optional.of(UserMapper.toBasicDto(entity));
    }

    @Override
    public List<UserBasicDto> findAllUsersWithEmail(String email) {
        return userRepository.findAllUsersWithMail(email).stream().map(UserMapper::toBasicDto).toList();
    }


    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }


    @Override
    public List<UserDto> findUsersByAge(int age) {
        return userRepository.findAllUserOlderByAge(age).stream().map(UserMapper::toDto).toList();
    }

    @Override
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userRepository.findAll().stream().map(UserMapper::toSimpleDto).toList();
    }

    @Override
    public List<UserDto> findAllUsersOlderThanDate(LocalDate time) {
        return userRepository.findAllUsersOlderThanDate(time).stream().map(UserMapper::toDto).toList();
    }


}