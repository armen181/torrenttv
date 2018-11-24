package net.ddns.armen181.torrenttv.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.domain.User;
import net.ddns.armen181.torrenttv.repository.CategoryRepository;
import net.ddns.armen181.torrenttv.repository.ChannelRepository;
import net.ddns.armen181.torrenttv.repository.UserRepository;
import net.ddns.armen181.torrenttv.service.ChannelService;
import net.ddns.armen181.torrenttv.service.UserService;
import net.ddns.armen181.torrenttv.util.AccessTranslation;
import net.ddns.armen181.torrenttv.util.Role;
import net.ddns.armen181.torrenttv.util.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ChannelService channelService;

    @Override
    @Transactional
    public User userRegistration(String name, String password, UserAccess userAccess, Role role, Boolean isLock) {

        User user = new User();
        user.setName(name);
        user.setUserPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
        user.setRole(role);

//        channelRepository.findAll().forEach(x -> {
//            if (role == Role.ADMIN || role == Role.VIP)
//                user.addFavouriteChannel(x);
//            else {
//                if (x.getAccessTranslation() == AccessTranslation.all)
//                    user.addFavouriteChannel(x);
//            }
//
//
//        });

        categoryRepository.findAll().forEach(user::addCategory);


        return userRepository.save(user);
    }

    @Override
    public User getByName(String name) {
        Assert.hasText(name, "name cannot be empty");
        return userRepository.findByName(name).orElseThrow(() -> new IllegalStateException("User not Fount"));
    }

    @Override
    public Set<Channel> getUserChannels(String name, int category) {
        Optional<User> user = userRepository.findByName(name);
        final Set<Channel> channels = new HashSet<>();
        user.ifPresent(user1 -> {
            user1.getCategories().iterator().forEachRemaining(x->{

               if (x.getCategoryIdOnApi()==category) {

                   x.getChannels().iterator().forEachRemaining(y->{

                       if(user1.getRole()==Role.ADMIN||user1.getRole()==Role.VIP){
                           channels.add(y);
                       }else if(y.getAccessTranslation()==AccessTranslation.all){
                           channels.add(y);
                       }

                   });
               }
           });
        });
        return channels;
    }


    @Override
    public Set<Category> getUserCategories(String name) {
        Optional<User> user = userRepository.findByName(name);
        return user.map(User::getCategories).orElseGet(HashSet::new);
    }

    @Override
    public Set<Channel> getUserFavourites(String name) {
        Set<Channel> channels = new HashSet<>();
        userRepository.findByName(name).ifPresent(user -> user.getFavouriteChannels().iterator().forEachRemaining(x -> {
            if (user.getRole() == Role.ADMIN || user.getRole() == Role.VIP)
                channels.add(x);
            else {
                if (x.getAccessTranslation() == AccessTranslation.all)
                    channels.add(x);
            }

        }));
        return channels;
    }
}


