package com.doghealth.DogHealth.Accessors;


import com.doghealth.DogHealth.Models.DhDog;
import com.doghealth.DogHealth.Models.DhFeeding;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedingAccessor extends CrudRepository<DhFeeding, Integer> {
    Iterable<DhFeeding> findDhFeedingByDateAndDhDog(String date, DhDog dog);
}
