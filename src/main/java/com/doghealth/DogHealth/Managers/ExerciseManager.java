package com.doghealth.DogHealth.Managers;

import com.doghealth.DogHealth.Accessors.DogAccessor;
import com.doghealth.DogHealth.Accessors.ExerciseAccessor;
import com.doghealth.DogHealth.Accessors.ExerciseTypeAccessor;
import com.doghealth.DogHealth.Models.DhDog;
import com.doghealth.DogHealth.Models.DhExercise;
import com.doghealth.DogHealth.Models.DhExerciseType;
import com.doghealth.DogHealth.Helpers.BusinessLogicHelpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;

@Service
public class ExerciseManager {

    @Autowired
    private ExerciseTypeAccessor exerciseTypeAccessor;

    @Autowired
    private DogAccessor dogAccessor;

    @Autowired
    private ExerciseAccessor exerciseAccessor;

    @Autowired
    private BusinessLogicHelpers bizHelper;
    public String add(String name, Double caloriesBurnedPerHour) {
        DhExerciseType exerciseType = new DhExerciseType(caloriesBurnedPerHour, name);
        exerciseTypeAccessor.save(exerciseType);
        return "success";
    }

    public DhExerciseType getDhExerciseByName(String name) {
        return exerciseTypeAccessor.getDhExerciseTypeByName(name);
    }

    public String delete(Integer id) {
        Optional<DhExerciseType> exerciseType = exerciseTypeAccessor.findById(id);
        if (!exerciseType.isPresent()) return "id not found";
        exerciseTypeAccessor.deleteById(id);
        return "success";
    }


    public String addDogExercise(Integer dogId, Integer exerciseTypeID, Double hours, String date) {
        Optional<DhDog> dog = dogAccessor.findById(dogId);
        if (!dog.isPresent()) return "did not find dog";
        Optional<DhExerciseType> exerciseType = exerciseTypeAccessor.findById(exerciseTypeID);
        if(!exerciseType.isPresent()) return "did not find exercise type";
        DhExercise exercise = new DhExercise(dog.get(), exerciseType.get(), hours, date);
        exerciseAccessor.save(exercise);
        return "success";
    }

    public String deleteDogExercise(Integer exerciseID) {
        Optional<DhExercise> exercise = exerciseAccessor.findById(exerciseID);
        if (!exercise.isPresent()) return "id not found";
        exerciseAccessor.deleteById(exerciseID);
        return "success";
    }

    public Iterable<DhExercise> getExerciseByDog(Integer dogId, String date) {
        Optional<DhDog> dog = dogAccessor.findById(dogId);
        if (!dog.isPresent()) return new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        return exerciseAccessor.findDhExerciseByDhDogAndDate(dog.get(), LocalDate.parse(date, formatter));
    }

    public int getCaloriesToDeductFromExercise(Integer dogId, String date) {
        return bizHelper.calculateCaloriesToDeduct(getExerciseByDog(dogId, date));
    }
}
