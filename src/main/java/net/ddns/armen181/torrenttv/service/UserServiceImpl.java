package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.domain.User;
import net.ddns.armen181.torrenttv.repository.UserRepository;
import net.ddns.armen181.torrenttv.util.Role;
import net.ddns.armen181.torrenttv.util.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User userRegistration(String name, String password, UserAccess userAccess, Role role, Boolean isLock) {
        User user = new User(name, BCrypt.hashpw(password, BCrypt.gensalt(12)), userAccess, role, isLock);
        return userRepository.save(user);
    }


}
