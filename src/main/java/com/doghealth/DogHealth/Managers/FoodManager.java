package com.doghealth.DogHealth.Managers;

import com.doghealth.DogHealth.Accessors.DogAccessor;
import com.doghealth.DogHealth.Accessors.FeedingAccessor;
import com.doghealth.DogHealth.Accessors.FoodTypeAccessor;
import com.doghealth.DogHealth.Models.*;
import com.doghealth.DogHealth.Helpers.BusinessLogicHelpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class FoodManager {

    @Autowired
    private FoodTypeAccessor foodTypeAccessor;

    @Autowired
    private DogAccessor dogAccessor;

    @Autowired
    private FeedingAccessor feedingAccessor;

    @Autowired
    private BusinessLogicHelpers bizHelper;

    public String add(String name, Double caloriesPerOz) {
        DhFoodType foodType = new DhFoodType(caloriesPerOz, name);
        foodTypeAccessor.save(foodType);
        return "success";
    }

    public DhFoodType getDhFoodTypeByName(String name) {
        return foodTypeAccessor.findDhFoodTypeByName(name);
    }

    public String delete(Integer id) {
        Optional<DhFoodType> dhFoodType = foodTypeAccessor.findById(id);
        if (!dhFoodType.isPresent()) return "id not found";
        foodTypeAccessor.deleteById(id);
        return "success";
    }


    public String addDogFeeding(Integer dogId, Integer foodTypeID, Double quantityInOz, String date) {
        Optional<DhDog> dog = dogAccessor.findById(dogId);
        if (!dog.isPresent()) return "did not find dog";
        Optional<DhFoodType> dhFoodType = foodTypeAccessor.findById(foodTypeID);
        if(!dhFoodType.isPresent()) return "did not find food type";
        DhFeeding feeding = new DhFeeding(dhFoodType.get(), dog.get(), date,  quantityInOz);
        feedingAccessor.save(feeding);
        return "success";
    }

    public String deleteDogFeeding(Integer feedingID) {
        Optional<DhFeeding> feeding = feedingAccessor.findById(feedingID);
        if (!feeding.isPresent()) return "id not found";
        feedingAccessor.deleteById(feedingID);
        return "success";
    }

    public Iterable<DhFeeding> getFeedingsByDog(Integer dogId, String date) {
        Optional<DhDog> dog = dogAccessor.findById(dogId);
        if (!dog.isPresent()) return new HashSet<>();
        return feedingAccessor.findDhFeedingByDateAndDhDog(date, dog.get());
    }

    public int getCaloriesToAddFromFeedings(Integer dogId, String date) {
        return bizHelper.calculateCaloriesToAdd(getFeedingsByDog(dogId, date));
    }
}