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
private ChannelService channelService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

         channelService.findChannelsFromTtvApi();

         userService.userRegistration(env.getProperty("admin.eMail"),env.getProperty("admin.firstName"),env.getProperty("admin.lastName"),env.getProperty("admin.password"),Role.ADMIN);
         userService.userRegistration(env.getProperty("user.eMail"),env.getProperty("user.firstName"),env.getProperty("user.lastName"),env.getProperty("user.password"),Role.valueOf(env.getProperty("user.role")));


          channelService.getCategory(0);

    }
}
