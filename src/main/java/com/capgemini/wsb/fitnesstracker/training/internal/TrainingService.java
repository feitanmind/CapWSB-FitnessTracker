package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.IUserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TrainingService implements ITrainingProvider, ITrainingService {

    private final ITrainingRepository trainingRepository;
    private final IUserProvider userProvider;

    @Override
    public TrainingDto getTraining(Long trainingId) {
        return TrainingMapper.toDto(trainingRepository.getReferenceById(trainingId));
    }

    @Override
    public List<TrainingDto> getListOfAllTrainings() {
        return trainingRepository.findAll().stream().map(TrainingMapper::toDto).toList();
    }

    @Override
    public List<TrainingDto> getTrainingsForSpecifiedUser(Long userId) {
        return trainingRepository.findAllTrainingsForUser(userId).stream().map(TrainingMapper::toDto).toList();
    }

    @Override
    public List<TrainingDto> getListOfTrainingForUserWithEndDateBeforeDate(Date date, Long userId) {
        return trainingRepository.findTrainingsWithEndDateBeforeSpecifiedEndDate(date,userId).stream().map(TrainingMapper::toDto).toList();
    }

    @Override
    public List<TrainingDto> getListOfTrainingForAllUsersWithEndDateBeforeDate(Date date) {
        return trainingRepository.findTrainingsWithEndDateBeforeSpecifiedEndDate(date).stream().map(TrainingMapper::toDto).toList();
    }

    @Override
    public List<TrainingDto> getListOfTrainingForUserWithSpecifiedActivity(ActivityType activityType, Long userId) {
        return trainingRepository.findAllTrainingForSpecifiedActivity(activityType,userId).stream().map(TrainingMapper::toDto).toList();
    }

    @Override
    public List<TrainingDto> getListOfTrainingForAllUsersWithSpecifiedActivity(ActivityType activityType) {
        return trainingRepository.findAllTrainingForSpecifiedActivity(activityType).stream().map(TrainingMapper::toDto).toList();
    }

    @Override
    public TrainingDto createTraining(TrainingDto training) {
        User user = userProvider.getUserEntity(training.getUser().Id());
        Training tr = TrainingMapper.toEntity(training,user);
        trainingRepository.saveAndFlush(tr);
        return TrainingMapper.toDto(tr);
    }

    @Override
    public TrainingDto updateTraining(TrainingDto trainingDto) {
        User user = userProvider.getUserEntity(trainingDto.getUser().Id());
        Training tr = TrainingMapper.toUpdateEntity(trainingDto,user);
        trainingRepository.saveAndFlush(tr);
        return TrainingMapper.toDto(tr);
    }
}
