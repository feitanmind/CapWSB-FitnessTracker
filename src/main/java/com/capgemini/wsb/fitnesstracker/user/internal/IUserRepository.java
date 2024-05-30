package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.Objects;
import java.util.Optional;
import java.util.List;
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

    /**
     * Return list of users older than user age
     * @param age
     * @return
     */
    default List<User> findAllUserOlderByAge(int age){
        int filter = Year.now().getValue()-age;
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(LocalDate.of(filter,1,1)))
                .toList();
    }

    //Tylko z powodu testów integracyjnych\
    default  List<User> findAllUsersWithMail(String email)
    {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail().toLowerCase(), email.toLowerCase()))
                .toList();
    }

    //tylko z powodu testów integracyjnych
    default List<User> findAllUsersOlderThanDate(LocalDate time)
    {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(time))
                .toList();
    }

}
