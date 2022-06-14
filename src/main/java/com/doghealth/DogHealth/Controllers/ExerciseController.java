package com.doghealth.DogHealth.Controllers;

import com.doghealth.DogHealth.Managers.ExerciseManager;
import com.doghealth.DogHealth.Models.DhExercise;
import com.doghealth.DogHealth.Models.DhExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseManager exerciseManager;

    @PostMapping(path="/addType")
    public @ResponseBody
    String addType(@RequestParam String name, @RequestParam Double caloriesBurnedPerHour) {
        return exerciseManager.add(name, caloriesBurnedPerHour);
    }

    @PostMapping(path="/deleteType")
    public @ResponseBody
    String deleteType(@RequestParam Integer id) {
        return exerciseManager.delete(id);
    }

    @PostMapping(path="/editType")
    public @ResponseBody
    String editType(@RequestBody DhExerciseType exerciseType) {
        return exerciseManager.edit(exerciseType);
    }

    @GetMapping(path="/findTypeByName")
    public @ResponseBody
    DhExerciseType findTypeByName(@RequestParam String name) {
        return exerciseManager.getDhExerciseByName(name);
    }

    @PostMapping(path="/addDogExercise")
    public @ResponseBody String addDogExercise(@RequestParam Integer dogId, @RequestParam Integer exerciseTypeID, @RequestParam Double hours, @RequestParam String date) {
        return exerciseManager.addDogExercise(dogId, exerciseTypeID, hours, date);
    }

    @GetMapping(path="/getExercisesByDog")
    public @ResponseBody Iterable<DhExercise> getExerciseByDog(@RequestParam Integer dogId, @RequestParam String date) {
        return exerciseManager.getExerciseByDog(dogId, date);
    }

    @PostMapping(path="/deleteDogExercise")
    public @ResponseBody String deleteDogExercise(@RequestParam Integer exerciseID) {
        return exerciseManager.deleteDogExercise(exerciseID);
    }

    @GetMapping(path = "/getCaloriesToDeductForDate")
    public @ResponseBody Integer getCaloriesToDeductForDate(@RequestParam Integer dogId, @RequestParam String date) {
        return exerciseManager.getCaloriesToDeductFromExercise(dogId, date);
    }
}
