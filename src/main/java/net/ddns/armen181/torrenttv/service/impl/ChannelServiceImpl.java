package net.ddns.armen181.torrenttv.service.impl;

import com.google.common.collect.Lists;
import net.ddns.armen181.torrenttv.DTO.ChannelsDTO;
import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.repository.CategoryRepository;
import net.ddns.armen181.torrenttv.repository.ChannelRepository;
import net.ddns.armen181.torrenttv.repository.ChannelsDao;
import net.ddns.armen181.torrenttv.service.ChannelService;
import net.ddns.armen181.torrenttv.service.TTVAPI;
import net.ddns.armen181.torrenttv.util.TTVType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    private TTVAPI ttvapi;
    private ChannelRepository channelRepository;
    private ChannelsDao channelsDao;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
    private EntityManager entityManager;

    public ChannelServiceImpl(
            TTVAPI ttvapi,
            ChannelRepository channelRepository,
            ChannelsDao channelsDao,
            CategoryRepository categoryRepository,
            ModelMapper modelMapper,
            EntityManager entityManager) {
        this.ttvapi = ttvapi;
        this.channelRepository = channelRepository;
        this.channelsDao = channelsDao;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    @Override

    @Transactional
    public Category channelList() {
        Arrays.stream(ttvapi.translationList(ttvapi.getSessionId(), TTVType.all).getCategories())
                .map(categoryDto -> new Category(categoryDto.getId(),categoryDto.getName(),categoryDto.getPosition(),categoryDto.getAdult()))
                .forEach(category -> categoryRepository.save(category));

        for (ChannelsDTO element : ttvapi.translationList(ttvapi.getSessionId(), TTVType.all).getChannels()) {
            Channel channel = new Channel(element.getId(), element.getName(), element.getLogo(), element.getEpg_id(), categoryRepository.findByCategoryIdOnApi(element.getGroup()).get());
            channelRepository.save(channel);
        }

        List<Category> categories = Lists.newArrayList(categoryRepository.findAll());
        return categories.get(1);


    }


    @Override
    public List<Channel> findChannelsByCategory(Integer group) {
        return channelsDao.findChannelsByCategory(group);
    }

    @Override
    public List<Channel> findChannelsByName(String name) {
        return channelsDao.findChannelsByName(name);
    }

    @Override
    public List<Category> getCategory(int id) {
       return Lists.newArrayList(categoryRepository.findAll());

    }
}
