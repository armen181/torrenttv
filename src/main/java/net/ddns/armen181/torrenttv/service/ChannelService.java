package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Channel;

import java.util.List;
import java.util.Set;

public interface ChannelService {
    Category channelList();
    List<Channel> findChannelsByCategoryAndAccess(Integer  group);
    List<Channel> findChannelsByNameAndAccess(String  name);
    Category getCategory(int id);
    Set<Channel> getChannelsByCategory(int categoryId);

}
