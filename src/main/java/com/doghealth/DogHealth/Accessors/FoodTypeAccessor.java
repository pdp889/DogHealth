package com.doghealth.DogHealth.Accessors;


import com.doghealth.DogHealth.Models.DhFoodType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTypeAccessor extends CrudRepository<DhFoodType, Integer> {
    DhFoodType findDhFoodTypeByName(String name);
}