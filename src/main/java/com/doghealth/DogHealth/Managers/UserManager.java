package com.doghealth.DogHealth.Managers;

import com.doghealth.DogHealth.Accessors.UserAccessor;
import com.doghealth.DogHealth.Models.DhUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    private UserAccessor userAccessor;

    public String addUser (String username, String email, String password)  {
        DhUser user = new DhUser(username, email, password);
        //check to see if username/email are unique, if not error out the addUser
        DhUser alreadyExisting = userAccessor.findByUsername(username);
        if (alreadyExisting != null) return "User already exists";

        //user does not exist, save and return ID
        return String.valueOf(userAccessor.save(user).getId());
    }

    public DhUser logIn(String username, String password) {
        return userAccessor.findByUsernameAndPassword(username, password);
    }


    public String edit(String username, String oldPassword, String newPassword) {
        DhUser user = userAccessor.findByUsernameAndPassword(username, oldPassword);
        if (user == null) return "unable to find username / password combo";
        user.setPassword(newPassword);
        userAccessor.save(user);
        return "success";
    }

    public String delete(String username) {
        DhUser user = userAccessor.findByUsername(username);
        if (user == null) return "unable to find username";
        userAccessor.deleteById(user.getId());
        return "success";
    }
}
