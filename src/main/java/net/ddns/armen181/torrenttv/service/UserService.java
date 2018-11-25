package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.domain.User;
import net.ddns.armen181.torrenttv.util.Role;
import net.ddns.armen181.torrenttv.util.UserAccess;

import java.util.List;
import java.util.Set;

public interface UserService {
    User userRegistration(String name, String password, UserAccess userAccess, Role role, Boolean isLock);
    User getByName(String name);
    Set<Channel> getUserChannels (String name, int category);
    Set<Category> getUserCategories(String name);
    Set<Channel> getUserFavourites (String name);
    Set<Channel> addUserFavourites(String name, String channel);
    Set<Channel> removeUserFavourites(String name, String channel);

}
