package com.doghealth.DogHealth.Accessors;

import com.doghealth.DogHealth.Models.DhDog;
import com.doghealth.DogHealth.Models.DhUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogAccessor extends CrudRepository<DhDog, Integer> {

    Iterable<DhDog> findDhDogByDhUser(DhUser user);


}

