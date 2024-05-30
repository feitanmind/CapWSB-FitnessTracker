package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface ITrainingRepository extends JpaRepository<Training, Long> {

    default List<Training> findAllTrainingsForUser(Long userId)
    {
        return findAll().stream().filter(t -> Objects.equals(t.getUser().getId(), userId)).toList();
    }
}
