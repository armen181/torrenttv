package net.ddns.armen181.torrenttv.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.ddns.armen181.torrenttv.DTO.TTVChannelDto;
import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.repository.CategoryRepository;
import net.ddns.armen181.torrenttv.repository.ChannelRepository;
import net.ddns.armen181.torrenttv.repository.ChannelsDao;
import net.ddns.armen181.torrenttv.service.ChannelService;
import net.ddns.armen181.torrenttv.service.TTVAPI;
import net.ddns.armen181.torrenttv.service.UserService;
import net.ddns.armen181.torrenttv.util.TtvType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ChannelServiceImpl implements ChannelService {

    private  final TTVAPI ttvapi;
    private final ChannelRepository channelRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    public ChannelServiceImpl(TTVAPI ttvapi,
                              ChannelRepository channelRepository,
                              CategoryRepository categoryRepository,
                              UserService userService) {
        this.ttvapi = ttvapi;
        this.channelRepository = channelRepository;
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
    public Category getCategory(Integer id) {
       return categoryRepository.findById(1L).isPresent()?categoryRepository.findById(1L).get():null;

    }

    @Override
    public Set<Channel> getChannelsByFavourites() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return userService.getUserFavourites(securityContext.getAuthentication().getName());
    }

    @Override
    public Set<Channel> getChannelsByCategory(Integer categoryId) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return userService.getUserChannels(securityContext.getAuthentication().getName(), categoryId);
    }

    @Override
    public TTVChannelDto getChannel(Integer id) {
        return ttvapi.translationStreamById(ttvapi.getSessionId(), id);
    }

    @Override
    public Set<Channel> addFavourite(String channelName) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return userService.addUserFavourites(securityContext.getAuthentication().getName(), channelName);
        

    }

    @Override
    public Set<Channel> removeFavourite(String channelName) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return userService.removeUserFavourites(securityContext.getAuthentication().getName(), channelName);
    }

    @Override
    public List<Category> getCategory() {
        return Lists.newArrayList(categoryRepository.findAll());
    }

}
