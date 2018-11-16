package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.domain.Channels;

import java.util.List;

public interface ChannelService {
    Channels channelList();
    List<Channels> findChannelsByCategory(Integer  group);
    List<Channels> findChannelsByName(String  name);

}
