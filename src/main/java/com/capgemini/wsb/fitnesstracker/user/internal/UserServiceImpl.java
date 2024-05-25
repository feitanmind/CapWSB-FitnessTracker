package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.IUserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.IUserService;
import com.capgemini.wsb.fitnesstracker.user.api.UserSimpleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void deleteUser(Long userId)
    {
        User user = userRepository.getReferenceById(userId);
        userRepository.delete(user);

    }
    @Override
    public Optional<UserDto> getUser(final Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User entity = user.get();
        return Optional.of(userMapper.toDto(entity));
    }

    @Override
    public Optional<UserSimpleDto> getUserByEmail(final String email) {
        Optional<User> user = userRepository.findByEmail(email);
        User entity = user.get();
        return Optional.of(userMapper.toSimpleDto(entity));
    }


    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    public List<UserDto> findUsersByAge(int age) {
        return userRepository.findAllUserOlderByAge(age).stream().map(userMapper::toDto).toList();
    }


}