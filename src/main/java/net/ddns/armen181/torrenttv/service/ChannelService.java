package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.util.AccessTranslation;

import java.util.List;
import java.util.Set;

public interface ChannelService {
    void findChannelsFromTtvApi();
    Category getCategory(int id);
    Set<Channel> getChannelsByFavourites();
    Set<Channel> getChannelsByCategory(int categoryId);
    Channel getChannel(long id);
    Set<Channel> addFavourite(String channelName);
    Set<Channel> removeFavourite(String channelName);

}
