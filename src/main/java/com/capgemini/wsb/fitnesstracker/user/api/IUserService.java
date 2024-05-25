package com.capgemini.wsb.fitnesstracker.user.api;


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
