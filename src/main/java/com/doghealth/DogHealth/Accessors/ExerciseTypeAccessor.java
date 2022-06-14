package com.doghealth.DogHealth.Accessors;

import com.doghealth.DogHealth.Models.DhExercise;
import com.doghealth.DogHealth.Models.DhExerciseType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseTypeAccessor extends CrudRepository<DhExerciseType, Integer> {
    DhExerciseType getDhExerciseTypeByName(String name);
}
