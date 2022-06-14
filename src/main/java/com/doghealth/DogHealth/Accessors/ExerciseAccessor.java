package com.doghealth.DogHealth.Accessors;

import com.doghealth.DogHealth.Models.DhDog;
import com.doghealth.DogHealth.Models.DhExercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseAccessor extends CrudRepository<DhExercise, Integer> {
    Iterable<DhExercise> findDhExerciseByDhDogAndDate(DhDog dog, String date);
}
