package net.ddns.armen181.torrenttv.controller;

import lombok.NonNull;
import net.ddns.armen181.torrenttv.DTO.TTVChannelDto;
import net.ddns.armen181.torrenttv.service.ChannelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class ChannelController {


    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping({"/channel"})
    public TTVChannelDto getChannel(@NonNull @RequestHeader Integer channelId){
        return channelService.getChannel(channelId);
    }
}
