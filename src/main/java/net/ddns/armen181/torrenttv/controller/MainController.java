package net.ddns.armen181.torrenttv.controller;

import lombok.NonNull;
import net.ddns.armen181.torrenttv.DTO.ChannelDto;
import net.ddns.armen181.torrenttv.DTO.ScreenShotDto;
import net.ddns.armen181.torrenttv.DTO.TTVAuthDto;
import net.ddns.armen181.torrenttv.DTO.TTVChannelDto;
import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.service.ChannelService;
import net.ddns.armen181.torrenttv.service.TTVAPI;
import net.ddns.armen181.torrenttv.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class MainController {
    private TTVAPI ttvapi;

    public MainController(TTVAPI ttvapi) {
        this.ttvapi = ttvapi;
        ttvapi.login("","");  // == load default value ==
    }

    @GetMapping({"/loginAPI"})
    public TTVAuthDto login(){
        return ttvapi.login("sss","sdsd");
    }


    @GetMapping({"/screen"})
    public ScreenShotDto getScreen(@NonNull @RequestHeader Integer channelId){
        return ttvapi.translationScreen(ttvapi.getSessionId(), channelId);
    }

}
