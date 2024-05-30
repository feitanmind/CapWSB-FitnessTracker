package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TrainingService implements ITrainingProvider, ITrainingService {

    private final ITrainingRepository trainingRepository;
    @Override
    public TrainingDto getTraining(Long trainingId) {
        return TrainingMapper.toDto(trainingRepository.getReferenceById(trainingId));
    }
}
