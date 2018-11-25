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
    private ChannelService channelService;
    private UserService userService;

    public MainController(TTVAPI ttvapi, ChannelService channelService, UserService userService) {
        this.ttvapi = ttvapi;
        this.channelService = channelService;
        this.userService = userService;
        ttvapi.login("","");  // == load default value ==
    }

    @GetMapping({"/loginAPI","/loginAPI.html"})
    public TTVAuthDto login(){
        return ttvapi.login("sss","sdsd");
    }


    @GetMapping({"/favourites","/favourites.html"})
    public Set<Channel> getFavourites(){
        return channelService.getChannelsByFavourites();
    }


    @GetMapping({"/category","/category.html"})
    public Set<Channel>  getCategory(@NonNull @RequestHeader int group){
        return channelService.getChannelsByCategory(group);
    }

    @GetMapping({"/addFavourites","/addFavourites.html"})
    public Set<Channel>  addFavourites(@NonNull @RequestHeader String name){

        return channelService.addFavourite(name);
    }
    @GetMapping({"/removeFavourites","/removeFavourites.html"})
    public Set<Channel>  removeFavourites(@NonNull @RequestHeader String name){

        return channelService.removeFavourite(name);
    }


    ///////////////////////////////////////////////////////////////////


    @GetMapping({"/channel","/channel.html"})
    public TTVChannelDto getChannel(@NonNull @RequestHeader int channelId){
        return ttvapi.translationStreamById(ttvapi.getSessionId(), channelId);
    }

    @GetMapping({"/screen","/screen.html"})
    public ScreenShotDto getScreen(@NonNull @RequestHeader int channelId){
        return ttvapi.translationScreen(ttvapi.getSessionId(), channelId);
    }

}
