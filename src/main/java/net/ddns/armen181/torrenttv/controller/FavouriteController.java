package net.ddns.armen181.torrenttv.controller;

import lombok.NonNull;
import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.service.ChannelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/rest")
public class FavouriteController {


    private final ChannelService channelService;

    public FavouriteController(ChannelService channelService) {
        this.channelService = channelService;
    }


    @GetMapping({"/addFavourites"})
    public Set<Channel> addFavourites(@NonNull @RequestHeader String name){
        return channelService.addFavourite(name);
    }

    @GetMapping({"/removeFavourites"})
    public Set<Channel>  removeFavourites(@NonNull @RequestHeader String name){
        return channelService.removeFavourite(name);
    }

    @GetMapping({"/favourites"})
    public Set<Channel> getFavourites(){
        return channelService.getChannelsByFavourites();
    }

}
