package net.ddns.armen181.torrenttv.controller;

import lombok.NonNull;
import net.ddns.armen181.torrenttv.DTO.ScreenShotDTO;
import net.ddns.armen181.torrenttv.DTO.TTVAuth;
import net.ddns.armen181.torrenttv.DTO.TTVChannel;
import net.ddns.armen181.torrenttv.DTO.TranslationList;
import net.ddns.armen181.torrenttv.service.ChannelService;
import net.ddns.armen181.torrenttv.service.TTVAPI;
import net.ddns.armen181.torrenttv.util.TTVType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private TTVAPI ttvapi;
    private ChannelService channelService;

    public MainController(TTVAPI ttvapi, ChannelService channelService) {
        this.ttvapi = ttvapi;
        this.channelService = channelService;
        ttvapi.login("","");  // == load default value ==
    }

    @GetMapping({"/loginAPI","/loginAPI.html"})
    public TTVAuth login(){
        return ttvapi.login("sss","sdsd");
    }

    @GetMapping({"/list","/list.html"})
    public TranslationList getList(@NonNull @RequestHeader int list){
        return ttvapi.translationList(ttvapi.getSessionId(), TTVType.values()[list]);
    }

    @GetMapping({"/channel","/channel.html"})
    public TTVChannel getChannel(@NonNull @RequestHeader int channelId){
        return ttvapi.translationStreamById(ttvapi.getSessionId(), channelId);
    }

    @GetMapping({"/screen","/screen.html"})
    public ScreenShotDTO getScreen(@NonNull @RequestHeader int channelId){
        return ttvapi.translationScreen(ttvapi.getSessionId(), channelId);
    }

}
