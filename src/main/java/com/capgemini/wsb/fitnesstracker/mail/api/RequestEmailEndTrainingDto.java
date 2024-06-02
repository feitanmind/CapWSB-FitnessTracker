package com.capgemini.wsb.fitnesstracker.mail.api;

import java.util.List;

public record RequestEmailEndTrainingDto (List<String> recipients, Long trainingId, boolean isTest){}
