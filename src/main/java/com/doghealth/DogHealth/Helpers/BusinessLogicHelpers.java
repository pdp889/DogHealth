package com.doghealth.DogHealth.Helpers;

import com.doghealth.DogHealth.Models.DhExercise;
import com.doghealth.DogHealth.Models.DhFeeding;
import org.springframework.stereotype.Service;

@Service
public class BusinessLogicHelpers {

    public int calculateCaloricIntakeNecessary(int weightLbs, boolean fixed) {
        // using RER, recommendation is to use 1.4x for fixed dog, vs 1.6x for non-fixed dog, but this includes estimagted activity.
        // Since we're calculating without any exercise, I'm going to set an arbitrary multiplier of 1.1x for non-fixed.
        // https://www.theveterinarynurse.com/review/article/nutritional-calculations-a-guide-for-the-veterinary-healthcare-team
        double multiplier = fixed ? 1.0 : 1.1;
        double weightInKg = 0.45359237 * weightLbs;
        return (int)(multiplier * 70 * Math.pow(weightInKg, 0.75));
    }

    public int calculateCaloriesToDeduct (Iterable<DhExercise> exerciseIterable) {
        int calories = 0;
        for (DhExercise e : exerciseIterable) {
            calories += e.getDurationInHours() * e.getDhExerciseType().getCaloriesBurnedPerHour();
        }
        return calories;
    }

    public int calculateCaloriesToAdd(Iterable<DhFeeding> feedingIterable) {
        int calories = 0;
        for (DhFeeding e : feedingIterable) {
            calories += e.getQuantityInOz() * e.getDhFoodType().getCaloriesPerOz();
        }
        return calories;
    }

}
