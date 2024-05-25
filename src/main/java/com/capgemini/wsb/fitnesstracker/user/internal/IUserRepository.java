package com.capgemini.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;
import java.util.Optional;

interface IUserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail().toLowerCase(), email.toLowerCase()))
                        .findFirst();
    }

}
