package net.ddns.armen181.torrenttv.bootstrap;

import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.repository.ChannelsDao;
import net.ddns.armen181.torrenttv.service.ChannelService;
import net.ddns.armen181.torrenttv.service.UserService;
import net.ddns.armen181.torrenttv.util.Role;
import net.ddns.armen181.torrenttv.util.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Bootstrap implements CommandLineRunner {

@Autowired
private Environment env;
@Autowired
private UserService userService;
@Autowired
private ChannelsDao channelsDao;
@Autowired
private ChannelService channelService;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        userService.userRegistration(env.getProperty("admin.username"),env.getProperty("admin.password"), UserAccess.All, Role.ADMIN,false);
        userService.userRegistration(env.getProperty("user.username"),env.getProperty("user.password"), UserAccess.BASE, Role.USER,false);
        userService.userRegistration(env.getProperty("vip.username"),env.getProperty("vip.password"), UserAccess.MIDDLE, Role.VIP,false);
        List<Channel> channels = channelService.channelList().getChannels();
        System.out.println(channels);
        channelService.getCategory(0);
//        channelsDao.findChannelsByCategory(4).forEach(System.out::println);
//        System.out.println(channelsDao.findChannelsByCategory(4).size());
//        System.out.println(channelsDao.findChannelsByName("Armenia TV Satellite"));
    }
}