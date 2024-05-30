package com.capgemini.wsb.fitnesstracker.training.api;

public interface ITrainingService {
    TrainingDto createTraining(TrainingSimpleDto training);
    TrainingDto updateTraining(TrainingDto trainingDto);
    TrainingDto updateTraining(Long trainingId, TrainingSimpleDto dto);
}
