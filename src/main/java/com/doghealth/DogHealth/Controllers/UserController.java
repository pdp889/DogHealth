package com.doghealth.DogHealth.Controllers;

import com.doghealth.DogHealth.Managers.UserManager;
import com.doghealth.DogHealth.Models.DhUser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserManager userManager;

    @PostMapping(path="/add")
    public @ResponseBody
    String addUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        return userManager.addUser(username, email, password);
    }
    @PostMapping(path="/login")
    public @ResponseBody boolean logIn(@RequestParam String username, @RequestParam String password) {
        return userManager.logIn(username, password) != null;
    }

    @PostMapping(path="/editPassword")
    public @ResponseBody String editPassword(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
        return userManager.edit(username, oldPassword, newPassword);
    }

    @PostMapping(path="/delete")
    public @ResponseBody String delete(@RequestParam String username) {
        return userManager.delete(username);
    }

}
