package net.ddns.armen181.torrenttv.domain;

import net.ddns.armen181.torrenttv.repository.CategoryRepository;
import net.ddns.armen181.torrenttv.repository.ChannelRepository;
import net.ddns.armen181.torrenttv.repository.UserRepository;
import net.ddns.armen181.torrenttv.util.AccessTranslation;
import net.ddns.armen181.torrenttv.util.Role;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class ChannelTest {
    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    UserRepository userRepository;

   private String name = "Test";
   private int channelNumber = 1;
   private AccessTranslation accessTranslation = AccessTranslation.all;
   private int epgNumber = 10;
   private int groupCategory = 2;
   private String logo = "/logo.jpg";

    @Before
    public void setUp() throws Exception {
        // === Create Channel
       Channel channel = new Channel();
       channel.setName(name);
       channel.setChannelNumber(channelNumber);
       channel.setAccessTranslation(accessTranslation);
       channel.setEpgNumber(epgNumber);
       channel.setGroupCategory(groupCategory);
       channel.setLogo(logo);
        User user = new User();
        user.setRole(Role.USER);
        user.setEMail("user");
        user.setUserPassword("1234");
        userRepository.save(user);
        channel.getUsers().add(user);
        channelRepository.save(channel); // === Saving channel


    }

    @Test
    public void getId() {
        channelRepository.findAll().iterator().forEachRemaining(System.out::println);
        System.out.println("==================");
        System.out.println(IterableUtils.size(channelRepository.findAll()));
    }

    @Test
    public void getChannelNumber() {
        System.out.println("==================");
        assertTrue(channelRepository.findByChannelNumber(channelNumber).isPresent());
    }

    @Test
    public void getName() {
        System.out.println("==================");
        assertTrue(channelRepository.findByName(name).isPresent());

    }

    @Test
    public void getGroupCategory() {
        System.out.println("==================");
        assertTrue(channelRepository.findByGroupCategory(groupCategory).isPresent());


    }

    @Test
    public void getLogo() {
        System.out.println("==================");
        assertTrue(channelRepository.findByLogo(logo).isPresent());


    }

    @Test
    public void getEpgNumber() {
        System.out.println("==================");
        assertTrue(channelRepository.findByEpgNumber(epgNumber).isPresent());

    }

    @Test
    public void getAccessTranslation() {
        System.out.println("==================");
        assertTrue(channelRepository.findByAccessTranslation(accessTranslation).isPresent());


    }

    @Test
    public void getUser() {

    }
}