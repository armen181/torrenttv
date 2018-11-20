package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.domain.User;
import net.ddns.armen181.torrenttv.util.Role;
import net.ddns.armen181.torrenttv.util.UserAccess;

import java.util.List;
import java.util.Set;

public interface UserService {
    User userRegistration(String name, String password, UserAccess userAccess, Role role, Boolean isLock, Set<Channel> channels);
    User getByName(String name);
}
