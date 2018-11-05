package net.ddns.armen181.torrenttv.controller;

import net.ddns.armen181.torrenttv.service.TTVAPI;
import net.ddns.armen181.torrenttv.util.TTVType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private TTVAPI ttvapi;

    public MainController(TTVAPI ttvapi) {
        this.ttvapi = ttvapi;
    }

    @GetMapping({"/login","/login.html"})
    public String login(){
        return ttvapi.login("sss","sdsd");
    }

    @GetMapping({"/list","/list"})
    public String getList(){
        return ttvapi.translationList(ttvapi.login("sss","sdsd"), TTVType.favourite);
    }

}
