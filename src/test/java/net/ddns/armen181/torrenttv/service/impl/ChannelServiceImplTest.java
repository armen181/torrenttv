package net.ddns.armen181.torrenttv.service.impl;

import net.ddns.armen181.torrenttv.repository.ChannelRepository;
import net.ddns.armen181.torrenttv.service.ChannelService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class ChannelServiceImplTest {

     ChannelServiceImpl channelService;
     @Mock
     ChannelRepository channelRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
       // channelService = new ChannelServiceImpl(channelRepository);
    }

    @Test
    public void addChannelList() {
    }

    @Test
    public void findChannelsByCategoryAndAccess() {
    }

    @Test
    public void findChannelsByNameAndAccess() {
    }

    @Test
    public void getCategory() {
    }

    @Test
    public void getChannelsByCategory() {
    }
}