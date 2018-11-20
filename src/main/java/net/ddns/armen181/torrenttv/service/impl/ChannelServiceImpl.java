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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ChannelServiceImpl implements ChannelService {
    private TTVAPI ttvapi;
    private ChannelRepository channelRepository;
    private ChannelsDao channelsDao;
    private CategoryRepository categoryRepository;
    private UserService userService;


    public ChannelServiceImpl(
            TTVAPI ttvapi,
            ChannelRepository channelRepository,
            ChannelsDao channelsDao,
            CategoryRepository categoryRepository,
            //ModelMapper modelMapper,
            EntityManager entityManager, UserService userService) {
        this.ttvapi = ttvapi;
        this.channelRepository = channelRepository;
        this.channelsDao = channelsDao;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Override

    @Transactional
    public Category addChannelList() {
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
            categoryRepository.findByCategoryIdOnApi(element.getGroup()).get().addChannel(channel);
            channelRepository.save(channel);
        });

       // List<Category> categories = Lists.newArrayList(categoryRepository.findAll());
        return null;//categories.get(1);


    }


    @Override
    public List<Channel> findChannelsByCategoryAndAccess(Integer group) {
        return channelsDao.findChannelsByCategory(group);
    }

    @Override
    public String findChannelsByNameAndAccess(String name) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(userService.getByName(securityContext.getAuthentication().getName()).getRole() != null && securityContext.getAuthentication().getName() != null){
            return userService.getByName(securityContext.getAuthentication().getName()).getRole().toString();
        }else {

            return "Please Login";
        }
        //return channelsDao.findChannelsByName(name);
    }

    @Override
    public List<Channel> findAllByAccessTranslationAndGroupCategory( int group) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(userService.getByName(securityContext.getAuthentication().getName()).getRole() != null && securityContext.getAuthentication().getName() != null){
        switch (userService.getByName(securityContext.getAuthentication().getName()).getRole()) {
            case ADMIN:
            case VIP:
                return channelRepository.findByGroupCategory(group).orElseThrow(()-> new IllegalStateException("Category List not found"));

            case USER:
                  return channelRepository.findAllByAccessTranslationAndGroupCategory(AccessTranslation.all,group).orElseThrow(()-> new IllegalStateException("Category List not found"));
        }
        }
        log.info("Cannot find user, please Login ");

        return null;
    }

    @Override
    public Category getCategory(int id) {
       return categoryRepository.findById(1L).isPresent()?categoryRepository.findById(1L).get():null;

    }

    @Override
    public Set<Channel> getChannelsByCategory(int categoryId) {
        return null;//categoryRepository.findById(1L).isPresent()?(Sets.newHashSet(categoryRepository.findById(1L).get().getChannels())):null;
    }
}
