package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Channel;

import java.util.List;

public interface ChannelService {
    Category channelList();
    List<Channel> findChannelsByCategory(Integer  group);
    List<Channel> findChannelsByName(String  name);
    List<Category> getCategory(int id);

}
