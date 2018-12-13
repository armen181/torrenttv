package net.ddns.armen181.torrenttv.controller;

import lombok.NonNull;
import net.ddns.armen181.torrenttv.DTO.UserDto;
import net.ddns.armen181.torrenttv.domain.User;
import net.ddns.armen181.torrenttv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping({"/register"})
    public User login(@Valid @RequestBody @NonNull UserDto userDto){

        return userService.userRegistration(userDto.getEMail(),
                userDto.getFirsName(),userDto.getLastName(),
                userDto.getUserPassword(),userDto.getRole()); //add favourite channel list
    }


}
