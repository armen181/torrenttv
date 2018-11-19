package net.ddns.armen181.torrenttv.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.ddns.armen181.torrenttv.DTO.ChannelDto;
import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.repository.CategoryRepository;
import net.ddns.armen181.torrenttv.repository.ChannelRepository;
import net.ddns.armen181.torrenttv.repository.ChannelsDao;
import net.ddns.armen181.torrenttv.service.ChannelService;
import net.ddns.armen181.torrenttv.service.TTVAPI;
import net.ddns.armen181.torrenttv.util.TtvType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            //ModelMapper modelMapper,
            EntityManager entityManager) {
        this.ttvapi = ttvapi;
        this.channelRepository = channelRepository;
        this.channelsDao = channelsDao;
        this.categoryRepository = categoryRepository;
       // this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    @Override

    @Transactional
    public Category channelList() {
        Arrays.stream(ttvapi.translationList(ttvapi.getSessionId(), TtvType.all).getCategories())
                .map(categoryDto -> new Category(categoryDto.getId(),categoryDto.getName(),categoryDto.getPosition(),categoryDto.getAdult()))
                .forEach(category -> categoryRepository.save(category));

        for (ChannelDto element : ttvapi.translationList(ttvapi.getSessionId(), TtvType.all).getChannels()) {
            Channel channel = new Channel(element.getId(), element.getName(), element.getLogo(), element.getEpg_id(), categoryRepository.findByCategoryIdOnApi(element.getGroup()).get(),element.getGroup(),element.getAccess_translation());
            channelRepository.save(channel);
        }

        List<Category> categories = Lists.newArrayList(categoryRepository.findAll());
        return categories.get(1);


    }


    @Override
    public List<Channel> findChannelsByCategoryAndAccess(Integer group) {
        return channelsDao.findChannelsByCategory(group);
    }

    @Override
    public List<Channel> findChannelsByNameAndAccess(String name) {
        return channelsDao.findChannelsByName(name);
    }

    @Override
    public Category getCategory(int id) {
       return categoryRepository.findById(1L).isPresent()?categoryRepository.findById(1L).get():null;

    }

    @Override
    public Set<Channel> getChannelsByCategory(int categoryId) {
        return categoryRepository.findById(1L).isPresent()?(Sets.newHashSet(categoryRepository.findById(1L).get().getChannels())):null;
    }
}
