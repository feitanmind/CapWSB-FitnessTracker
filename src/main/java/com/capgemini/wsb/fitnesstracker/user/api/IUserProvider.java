package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

public interface IUserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<UserDto> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<UserBasicDto> getUserByEmail(String email);

    //Z powodu testów (bo nie powinno być wielu użytkowników z takim samym emailem
    /**
     * Find all users with specified email
     * @param email
     * @return List
     */
    List<UserBasicDto> findAllUsersWithEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<UserDto> findAllUsers();

    /**
     * Return all users older than passed age
     * @param age
     * @return List
     */
    List<UserDto> findUsersByAge(int age);

    /**
     * Return all simple users
     * @return List
     */
    List<UserSimpleDto> getAllSimpleUsers();
}
