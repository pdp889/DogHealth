package com.doghealth.DogHealth.Controllers;

import com.doghealth.DogHealth.Managers.FoodManager;
import com.doghealth.DogHealth.Models.DhFeeding;
import com.doghealth.DogHealth.Models.DhFoodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/food")
public class FoodController {

    @Autowired
    private FoodManager foodManager;

    @PostMapping(path="/addType")
    public @ResponseBody
    String addType(@RequestParam String name, @RequestParam Double caloriesPerOz) {
        return foodManager.add(name, caloriesPerOz);
    }

    @PostMapping(path="/deleteType")
    public @ResponseBody
    String deleteType(@RequestParam Integer id) {
        return foodManager.delete(id);
    }

    @GetMapping(path="/findTypeByName")
    public @ResponseBody
    DhFoodType findTypeByName(@RequestParam String name) {
        return foodManager.getDhFoodTypeByName(name);
    }

    @PostMapping(path="/addDogFeeding")
    public @ResponseBody String addDogFeeding(@RequestParam Integer dogId, @RequestParam Integer foodTypeID, @RequestParam Double quantityInOz, @RequestParam String date) {
        return foodManager.addDogFeeding(dogId, foodTypeID, quantityInOz, date);
    }

    @GetMapping(path="/getFeedingsByDog")
    public @ResponseBody Iterable<DhFeeding> getFeedingsByDog(@RequestParam Integer dogId, @RequestParam String date) {
        return foodManager.getFeedingsByDog(dogId, date);
    }

    @PostMapping(path="/deleteDogFeeding")
    public @ResponseBody String deleteDogFeeding(@RequestParam Integer feedingID) {
        return foodManager.deleteDogFeeding(feedingID);
    }

    @GetMapping(path = "/getCaloriesToAddForDate")
    public @ResponseBody Integer getCaloriesToAddForDate(@RequestParam Integer dogId, @RequestParam String date) {
        return foodManager.getCaloriesToAddFromFeedings(dogId, date);
    }
}