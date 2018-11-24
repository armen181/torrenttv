package net.ddns.armen181.torrenttv.service.impl;

import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.repository.CategoryRepository;
import net.ddns.armen181.torrenttv.repository.ChannelRepository;
import net.ddns.armen181.torrenttv.repository.ChannelsDao;
import net.ddns.armen181.torrenttv.service.ChannelService;
import net.ddns.armen181.torrenttv.service.TTVAPI;
import net.ddns.armen181.torrenttv.service.UserService;
import net.ddns.armen181.torrenttv.util.AccessTranslation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
//@SpringBootTest
//@DataJpaTest
//@AutoConfigureDataJpa
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChannelServiceImplTest {

    @MockBean
    ChannelServiceImpl channelService;

    @Autowired
    private ChannelRepository channelRepository;


    @Before
    public void setUp() throws Exception {
     //   MockitoAnnotations.initMocks(this);
    //    channelService = new ChannelServiceImpl(ttvapi,channelRepository,channelsDao,categoryRepository,userService);
    }

    @Test
    public void addChannelList() {
        Channel channel = new Channel();

    }

    @Test
    public void findChannelsByCategoryAndAccess() {
//        List<Channel> list = channelService.findChannelsByCategoryAndAccess(1);
//        System.out.println(list.size());
//        assertEquals(list.size(),0);
    }

    @Test
    public void findChannelsByNameAndAccess() {
//      System.out.println(test);

    }

    @Test
    public void getCategory() {
     //   Set<Channel> channels = channelService.getChannelsByCategory(1);
    //assertEquals(channels.size(),0);
    }

    @Test
    public void getChannelsByCategory() {
    }

    @Test
    public void getChannel() {
        Channel channel = new Channel(1,"",1,"",1,AccessTranslation.all);
        Channel channel1 = new Channel(2,"",1,"",1,AccessTranslation.all);
        channelRepository.save(channel);
        channelRepository.save(channel1);
      //  channelService.saveChannel(channel);
        System.out.println("############");
        System.out.println(channelService.getChannel(1l));
//        channelRepository.findAll().iterator().forEachRemaining(System.out::println);
      //  System.out.println(channelService.getChannel(0l));
         //channelService.saveChannel(channel);
//
//        when(channelService.getChannel(0L)).thenReturn(channel);
//        Channel channel1 = channelService.getChannel(0L);
//        System.out.println(channel1.getName());
//        assertEquals(channel1,channel);
        //verify(channelRepository, times(1)).findByChannelNumber(0);
    }

}