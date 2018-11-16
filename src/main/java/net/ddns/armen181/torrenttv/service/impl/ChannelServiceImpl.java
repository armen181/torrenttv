package net.ddns.armen181.torrenttv.service.impl;

import net.ddns.armen181.torrenttv.DTO.ChannelsDTO;
import net.ddns.armen181.torrenttv.domain.Channels;
import net.ddns.armen181.torrenttv.repository.ChannelRepository;
import net.ddns.armen181.torrenttv.repository.ChannelsDao;
import net.ddns.armen181.torrenttv.service.ChannelService;
import net.ddns.armen181.torrenttv.service.TTVAPI;
import net.ddns.armen181.torrenttv.util.TTVType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private TTVAPI ttvapi;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ChannelsDao channelsDao;

    @Override
    public Channels channelList() {
        for (ChannelsDTO channel : ttvapi.translationList(ttvapi.getSessionId(), TTVType.all).getChannels()) {
            Channels channels = new Channels(channel.getId(),channel.getName(),channel.getGroup(),channel.getLogo(),channel.getEpg_id());
            channelRepository.save(channels);
        }
        return null;
    }

    @Override
    public List<Channels> findChannelsByCategory(Integer group) {
        return channelsDao.findChannelsByCategory(group);
    }

    @Override
    public List<Channels> findChannelsByName(String name) {
        return channelsDao.findChannelsByName(name);
    }
}
