package com.doghealth.DogHealth.Accessors;

import com.doghealth.DogHealth.Models.DhDog;
import com.doghealth.DogHealth.Models.DhWeight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightAccessor extends CrudRepository<DhWeight, Integer> {
    Iterable<DhWeight> findDhWeightByDhDog(DhDog dog);

}