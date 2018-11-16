package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.domain.User;
import net.ddns.armen181.torrenttv.util.Role;
import net.ddns.armen181.torrenttv.util.UserAccess;

public interface UserService {
    User userRegistration(String name, String password, UserAccess userAccess, Role role, Boolean isLock);

}
