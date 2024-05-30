package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.ITrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.ITrainingService;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingSimpleDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final ITrainingProvider trainingProvider;
    private final ITrainingService trainingService;

    @GetMapping("{userId}")
    public List<TrainingDto> getTraining(@PathVariable("userId") Long userId)
    {
        return trainingProvider.getTrainingsForSpecifiedUser(userId);
    }
    @GetMapping
    public List<TrainingDto> getListOfTrainings()
    {
        return  trainingProvider.getListOfAllTrainings();
    }
    @GetMapping("/user/{userId}")
    public List<TrainingDto> getListOfTrainingsForUser(@PathVariable("userId") Long userId)
    {
        return trainingProvider.getTrainingsForSpecifiedUser(userId);
    }
    @GetMapping("/activityType")
    public List<TrainingDto> getListOfTrainingsWithSpecifiedActivity(@Param("activityType") String activityType)
    {
        return trainingProvider.getListOfTrainingForAllUsersWithSpecifiedActivity(ActivityType.valueOf(activityType));
    }
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TrainingDto addTraining(@RequestBody TrainingSimpleDto dto)
    {
        return trainingService.createTraining(dto);
    }
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getListOfTrainingsAfterSpecifiedDate(@PathVariable("afterTime") String afterTime)
    {
        LocalDate date = LocalDate.parse(afterTime);
        Date d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return trainingProvider.getListOfTrainingForAllUsersWithEndDateAfterDate(d);
    }
    @PutMapping("{trainingId}")
    public TrainingDto updateTraining(@PathVariable("trainingId") Long trainingId, @RequestBody TrainingSimpleDto dto)
    {
        return trainingService.updateTraining(trainingId,dto);
    }

}
