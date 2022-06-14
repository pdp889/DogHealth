package com.doghealth.DogHealth.Accessors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.doghealth.DogHealth.Models.DhUser;
import org.springframework.stereotype.Repository;

//auto implemented by spring into a bean called userAccessor
@Repository
public interface UserAccessor extends CrudRepository<DhUser, Integer> {

@Query("select u from DhUser u where u.username = ?1")
    DhUser findByUsername(String username);

@Query("select u from DhUser u where u.username = ?1 and u.password = ?2")
    DhUser findByUsernameAndPassword(String username, String password);
}
