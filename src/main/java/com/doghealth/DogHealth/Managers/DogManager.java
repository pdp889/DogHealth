package com.doghealth.DogHealth.Managers;

import com.doghealth.DogHealth.Accessors.DogAccessor;
import com.doghealth.DogHealth.Accessors.UserAccessor;
import com.doghealth.DogHealth.Accessors.WeightAccessor;
import com.doghealth.DogHealth.Models.DhUser;
import com.doghealth.DogHealth.Models.DhDog;
import com.doghealth.DogHealth.Models.DhWeight;
import com.doghealth.DogHealth.Helpers.BusinessLogicHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DogManager {

    @Autowired
    private DogAccessor dogAccessor;
    @Autowired
    private UserAccessor userAccessor;
    @Autowired
    private WeightAccessor weightAccessor;

    @Autowired
    private BusinessLogicHelpers bizHelper;

    public String add(String username, String name, Integer gender, Boolean fixed) {
        DhUser user = userAccessor.findByUsername(username);
        if (user == null) return "username not found";
        DhDog dog = new DhDog(name, gender, fixed, user);
        DhDog returnObj = dogAccessor.save(dog);

        return Integer.toString(returnObj.getId());
    }

    public Iterable<DhDog> getDogsByUsername(String username) {
        DhUser user = userAccessor.findByUsername(username);
        return dogAccessor.findDhDogByDhUser(user);
    }

    public DhDog edit(DhDog dogInput) {
        //is this going to break?
        if (dogInput == null || dogInput.getId() < 1) return new DhDog();
        DhDog dog = dogAccessor.findById(dogInput.getId()).get();
        if (dog == null) return new DhDog();
        //this is ugly, and I should just write a custom update query
        // possibly because of the list of dogs associated with each user?
        dogInput.setDhUser(userAccessor.findById(dogInput.getDhUser().getId()).get());
        return dogAccessor.save(dogInput);
    }

    public String delete(Integer dogID) {
        Optional<DhDog> dog = dogAccessor.findById(dogID);
        if (!dog.isPresent()) return "dog not found";
        dogAccessor.deleteById(dogID);
        return "success";
    }

    public String addWeight(Integer dogID, Double weight, String date) {
        Optional<DhDog> dog = dogAccessor.findById(dogID);
        if (!dog.isPresent()) return "dog not found";
        DhWeight dhWeight = new DhWeight(weight, dog.get(), date);
        weightAccessor.save(dhWeight);
        return "success";
    }

    public DhWeight getWeightById(Integer id) {
        Optional<DhWeight> weight = weightAccessor.findById(id);
        return (weight.isPresent()) ? weight.get() : null;
    }

    public String deleteWeight(Integer weightID) {
        Optional<DhWeight> weight = weightAccessor.findById(weightID);
        if (!weight.isPresent()) return "weightID not found";
        weightAccessor.deleteById(weightID);
        return "success";
    }

    public Iterable<DhWeight> getWeightsByDog(Integer dogID) {
        Optional<DhDog> dog = dogAccessor.findById(dogID);
        if (!dog.isPresent()) return null;
        Iterable<DhWeight> weightList = weightAccessor.findDhWeightByDhDog(dog.get());
        return weightList;
    }

    public Integer getCaloricIntake(Integer dogID) {
        Optional<DhDog> dog = dogAccessor.findById(dogID);
        if (!dog.isPresent()) return -1;
        Iterable<DhWeight> weightList = weightAccessor.findDhWeightByDhDog(dog.get());

        double weight = 0.0;
        LocalDate currentDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate lastDate = LocalDate.parse("10-10-1950", formatter);

        for (DhWeight dhWeight : weightList) {
            currentDate = dhWeight.getDate();
            if (currentDate.isAfter(lastDate)) {
                lastDate = currentDate;
                weight = dhWeight.getWeight();
            }
        }


        return bizHelper.calculateCaloricIntakeNecessary((int) weight, dog.get().getFixed());
    }
}
