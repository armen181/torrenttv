package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.DTO.CategoryDto;
import net.ddns.armen181.torrenttv.DTO.TTVChannelDto;
import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Channel;

import java.util.List;
import java.util.Set;

public interface ChannelService {
    void findChannelsFromTtvApi();
    Category getCategory(Integer id);
    Set<Channel> getChannelsByFavourites();
    Set<Channel> getChannelsByCategory(Integer categoryId);
    TTVChannelDto getChannel(Integer id);
    Set<Channel> addFavourite(String channelName);
    Set<Channel> removeFavourite(String channelName);
    List<Category> getCategory();

}
