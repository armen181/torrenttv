package net.ddns.armen181.torrenttv.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.repository.CategoryRepository;
import net.ddns.armen181.torrenttv.repository.ChannelRepository;
import net.ddns.armen181.torrenttv.repository.ChannelsDao;
import net.ddns.armen181.torrenttv.service.ChannelService;
import net.ddns.armen181.torrenttv.service.TTVAPI;
import net.ddns.armen181.torrenttv.service.UserService;
import net.ddns.armen181.torrenttv.util.AccessTranslation;
import net.ddns.armen181.torrenttv.util.TtvType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class ChannelServiceImpl implements ChannelService {


    private  final TTVAPI ttvapi;

    private final ChannelRepository channelRepository;

    private final ChannelsDao channelsDao;

    private final CategoryRepository categoryRepository;

    private final UserService userService;

    public ChannelServiceImpl(TTVAPI ttvapi, ChannelRepository channelRepository, ChannelsDao channelsDao, CategoryRepository categoryRepository, UserService userService) {
        this.ttvapi = ttvapi;
        this.channelRepository = channelRepository;
        this.channelsDao = channelsDao;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void findChannelsFromTtvApi() {
        ttvapi.translationList(ttvapi.getSessionId(), TtvType.all).getCategories().forEach(x-> {
           Category category = new Category();
           category.setCategoryIdOnApi(x.getId());
           category.setAdult(x.getAdult());
           category.setName(x.getName());
           category.setPosition(x.getPosition());
            categoryRepository.save(category);
    });

        ttvapi.translationList(ttvapi.getSessionId(), TtvType.all).getChannels().forEach(element -> {
            Channel channel = new Channel();
            channel.setChannelNumber(element.getId());
            channel.setEpgNumber(element.getEpg_id());
            channel.setAccessTranslation(element.getAccess_translation());
            channel.setGroupCategory(element.getGroup());
            channel.setLogo(element.getLogo());
            channel.setName(element.getName());
            categoryRepository.findByCategoryIdOnApi(element.getGroup()).ifPresent(x->x.addChannel(channel));
            channelRepository.save(channel);
        });
    }


    @Override
    public Category getCategory(int id) {
       return categoryRepository.findById(1L).isPresent()?categoryRepository.findById(1L).get():null;

    }

    @Override
    public Set<Channel> getChannelsByFavourites() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return userService.getUserFavourites(securityContext.getAuthentication().getName());
    }

    @Override
    public Set<Channel> getChannelsByCategory(int categoryId) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return userService.getUserChannels(securityContext.getAuthentication().getName(), categoryId);
    }

    @Override
    public Channel getChannel(long id) {
        return null;
    }

}
