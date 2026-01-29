package edu.ban7.demo_spring_bsd_24_26.controller;

import edu.ban7.demo_spring_bsd_24_26.dao.AppUserDao;
import edu.ban7.demo_spring_bsd_24_26.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class AppUserController {

    @Autowired
    protected AppUserDao appUserDao;

    @GetMapping("/user/list")
    public List<AppUser> getAppUser() {
        return appUserDao.findAll();
    }

    @GetMapping("/user/{id}")
    public AppUser getAppUserById(@PathVariable int id) {

        Optional<AppUser> optionalAppUser = appUserDao.findById(id);

        return optionalAppUser.orElse(null);

    }

    @PostMapping("/user")
    public AppUser saveAppUser(@RequestBody AppUser appUser) {
        appUserDao.save(appUser);
        return appUser;
    }

}
