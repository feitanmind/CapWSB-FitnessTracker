package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.ITrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.ITrainingService;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/training")
@RequiredArgsConstructor
public class TrainingController {
    public ITrainingProvider trainingProvider;
    public ITrainingService trainingService;

    @GetMapping("{id}")
    public TrainingDto getTraining(@PathVariable("id") Long id)
    {
        return trainingProvider.getTraining(id);
    }
}
