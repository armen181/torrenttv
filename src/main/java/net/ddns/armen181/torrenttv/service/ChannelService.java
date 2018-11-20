package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.util.AccessTranslation;

import java.util.List;
import java.util.Set;

public interface ChannelService {
    Category addChannelList();
    List<Channel> findChannelsByCategoryAndAccess(Integer  group);
    String findChannelsByNameAndAccess(String  name);
    List<Channel> findAllByAccessTranslationAndGroupCategory( int group);
    Category getCategory(int id);
    Set<Channel> getChannelsByCategory(int categoryId);

}
