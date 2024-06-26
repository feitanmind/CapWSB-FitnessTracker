package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.*;
import com.capgemini.wsb.fitnesstracker.training.events.TrainingEndEventPublisher;
import com.capgemini.wsb.fitnesstracker.user.api.IUserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
class TrainingService implements ITrainingProvider, ITrainingService {
    private final List<TrainingSendDto> trainingSendDtos = new ArrayList<>();
    private final ITrainingRepository trainingRepository;
    private final IUserProvider userProvider;
    private final TrainingEndEventPublisher trainingEndEventPublisher;

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
    public List<TrainingDto> getListOfTrainingForAllUsersWithEndDateAfterDate(Date date) {
        return trainingRepository.findTrainingsWithEndDateAfterSpecifiedEndDate(date).stream().map(TrainingMapper::toDto).toList();
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
    public TrainingDto createTraining(TrainingSimpleDto training) {
        User user = userProvider.getUserEntity(training.getUserId());
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

    @Override
    public TrainingDto updateTraining(Long trainingId, TrainingSimpleDto dto) {
        User user = trainingRepository.getReferenceById(trainingId).getUser();
        Training tr = TrainingMapper.toUpdateEntity(dto,user,trainingId);
        trainingRepository.saveAndFlush(tr);
        return  TrainingMapper.toDto(tr);
    }
    //ta metoda fajna by była do sprawdzania czy zostały wysłane powiadomienia o zakonczonych treningach
    private void checkAndSendUnsendEmailsAboutEndedTraining()
    {
        List<TrainingDto> trainingDtos = getListOfAllTrainings();
        for (TrainingDto trainingDto : trainingDtos) {
            boolean isSend = false;
            for(TrainingSendDto trainingSendDto : trainingSendDtos) {
                if(trainingDto.getId() == trainingSendDto.trainingId())
                {
                    //też spoko jakby w modelu było czy wysyłać powiadomienie do użytkownika w booleanie bo niektórzy nie chcą spamu na poczcie
                    isSend = true;
                    break;
                }
            }
            if(!isSend && trainingDto.getEndTime().before(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))){
                trainingEndEventPublisher.sendMessageAndPublishEvent("Zakończono trening o id: "+trainingDto.getId(),trainingDto.getId());
            }
        }
    }
}
