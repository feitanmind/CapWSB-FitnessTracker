package com.capgemini.wsb.fitnesstracker.user.api;


import com.capgemini.wsb.fitnesstracker.user.internal.User;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface IUserService {
    /**
     * Create user when userdto was passed
     * @param userDto
     * @return
     */
    UserDto createUser(UserDto userDto);

    /**
     * Delete user when id was passed
     * @param userId
     */
    void deleteUser(Long userId);

    /**
     * Update user when userDto was passed
     * @param userDto
     * @return
     */
    UserDto updateUser(UserDto userDto);
}
