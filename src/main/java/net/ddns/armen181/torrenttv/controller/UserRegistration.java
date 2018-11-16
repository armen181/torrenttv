package net.ddns.armen181.torrenttv.controller;

import lombok.NonNull;
import net.ddns.armen181.torrenttv.domain.User;
import net.ddns.armen181.torrenttv.service.UserService;
import net.ddns.armen181.torrenttv.util.Role;
import net.ddns.armen181.torrenttv.util.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistration {

    @Autowired
    private UserService userService;

    @GetMapping({"/register","/register.html"})
    public User login(@RequestHeader @NonNull String name, @RequestHeader @NonNull String password){
        return userService.userRegistration(name,password, UserAccess.BASE, Role.USER,false);
    }

}
