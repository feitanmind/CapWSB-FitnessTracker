package com.capgemini.wsb.fitnesstracker.training.api;

public interface ITrainingService {
    TrainingDto createTraining(TrainingDto training);
    TrainingDto updateTraining(TrainingDto trainingDto);
}
