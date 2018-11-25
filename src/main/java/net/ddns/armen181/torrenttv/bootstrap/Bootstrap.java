package net.ddns.armen181.torrenttv.bootstrap;

import net.ddns.armen181.torrenttv.repository.ChannelRepository;
import net.ddns.armen181.torrenttv.repository.ChannelsDao;
import net.ddns.armen181.torrenttv.repository.UserRepository;
import net.ddns.armen181.torrenttv.service.ChannelService;
import net.ddns.armen181.torrenttv.service.UserService;
import net.ddns.armen181.torrenttv.util.AccessTranslation;
import net.ddns.armen181.torrenttv.util.Role;
import net.ddns.armen181.torrenttv.util.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Autowired
private UserRepository userRepository;
@Autowired
private ChannelRepository channelRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

         channelService.findChannelsFromTtvApi();


          userService.userRegistration(env.getProperty("admin.username"),env.getProperty("admin.password"), UserAccess.All, Role.ADMIN,false);
          userService.userRegistration(env.getProperty("user.username"),env.getProperty("user.password"), UserAccess.BASE, Role.USER,false);
        //userService.userRegistration(env.getProperty("user.username"),env.getProperty("user.password"), UserAccess.BASE, Role.USER,false,null);//channelService.getChannelsByCategory(2));
       // userService.userRegistration(env.getProperty("vip.username"),env.getProperty("vip.password"), UserAccess.MIDDLE, Role.VIP,false,null);//channelService.getChannelsByCategory(3));


        channelService.getCategory(0);

    }
}
