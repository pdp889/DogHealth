package com.doghealth.DogHealth.Controllers;

import com.doghealth.DogHealth.Managers.DogManager;
import com.doghealth.DogHealth.Models.DhDog;
import com.doghealth.DogHealth.Models.DhWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/dog")
public class DogController {
    @Autowired
    private DogManager dogManager;

    @PostMapping(path="/add")
    public @ResponseBody
    String addUser(@RequestParam String username, @RequestParam String name, @RequestParam Integer gender, @RequestParam Boolean fixed) {
        return dogManager.add(username, name, gender, fixed);
    }

    @GetMapping(path="/getDogsByUsername")
    public @ResponseBody
    Iterable<DhDog> getDogsByUsername(@RequestParam String username) {
        return dogManager.getDogsByUsername(username);
    }

    @PostMapping(path="/edit")
    public @ResponseBody DhDog edit(@RequestBody DhDog dog) {
        return dogManager.edit(dog);
    }

    @PostMapping(path="/delete")
    public @ResponseBody String delete(@RequestParam Integer dogID) {
        return dogManager.delete(dogID);
    }

    @PostMapping(path="/addWeight")
    public @ResponseBody String addWeight(@RequestParam Integer dogID, @RequestParam Double weight, @RequestParam String date) {
        return dogManager.addWeight(dogID, weight, date);
    }

    @PostMapping(path="/deleteWeight")
    public @ResponseBody String deleteWeight(@RequestParam Integer weightID) {
        return dogManager.deleteWeight(weightID);
    }

    @GetMapping(path="/getWeightsByDog")
    public @ResponseBody
    Iterable<DhWeight> getWeightsByDog(@RequestParam Integer dogID) {
        Iterable<DhWeight> weightList= dogManager.getWeightsByDog(dogID);
        return weightList;
    }

    @GetMapping(path="/getWeightByID")
    public @ResponseBody
    DhWeight getWeightByID(@RequestParam Integer weightID) {
        return dogManager.getWeightById(weightID);
    }

    @GetMapping(path="/getBaseCaloricIntakeNecessary")
    public @ResponseBody Integer getBaseCaloricIntakeNecessary(@RequestParam Integer dogID){
        return dogManager.getCaloricIntake(dogID);
    }

}
