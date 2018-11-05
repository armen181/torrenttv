package net.ddns.armen181.torrenttv.controller;

import lombok.NonNull;
import net.ddns.armen181.torrenttv.DTO.TTVAuth;
import net.ddns.armen181.torrenttv.DTO.TTVChannel;
import net.ddns.armen181.torrenttv.DTO.TranslationList;
import net.ddns.armen181.torrenttv.service.TTVAPI;
import net.ddns.armen181.torrenttv.util.TTVType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private TTVAPI ttvapi;

    public MainController(TTVAPI ttvapi) {
        this.ttvapi = ttvapi;
    }

    @GetMapping({"/login","/login.html"})
    public TTVAuth login(){
        return ttvapi.login("sss","sdsd");
    }

    @GetMapping({"/list","/list.html"})
    public TranslationList getList(@NonNull @RequestHeader int list){
        return ttvapi.translationList(ttvapi.login("sss","sdsd").getSession(), TTVType.values()[list]);
    }

    @GetMapping({"/channel","/channel.html"})
    public TTVChannel getChannel(@NonNull @RequestHeader int channelId){
        return ttvapi.translationStreamById(ttvapi.login("sss","sdsd").getSession(), channelId);
    }

}
