package net.ddns.armen181.torrenttv.service.impl;

import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.domain.User;
import net.ddns.armen181.torrenttv.repository.ChannelRepository;
import net.ddns.armen181.torrenttv.repository.UserRepository;
import net.ddns.armen181.torrenttv.service.UserService;
import net.ddns.armen181.torrenttv.util.AccessTranslation;
import net.ddns.armen181.torrenttv.util.Role;
import net.ddns.armen181.torrenttv.util.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public User userRegistration(String name, String password, UserAccess userAccess, Role role, Boolean isLock, Set<Channel> channels) {
        User user = new User();
        user.setName(name);
        user.setUserPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
        user.setRole(role);
        Channel channel = new Channel(10,"test",15,"fgfg",45, AccessTranslation.all);
        channelRepository.save(channel);
        Channel channel1 = new Channel(15,"test11",15,"fgfg",45, AccessTranslation.all);
        channelRepository.save(channel1);
        user.getChannels().add(channel);//.addChannel(channel);
        user.getChannels().add(channel1);


        return userRepository.save(user);
    }

    @Override
    public User getByName(String name) {
        Assert.hasText(name, "name cannot be empty");
        return userRepository.findByName(name).orElseThrow(()->new IllegalStateException("User not Fount"));
    }


}
