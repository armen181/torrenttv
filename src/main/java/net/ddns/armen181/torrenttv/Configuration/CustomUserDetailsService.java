package net.ddns.armen181.torrenttv.Configuration;


import net.ddns.armen181.torrenttv.domain.User;
import net.ddns.armen181.torrenttv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEMail(s);
        return user.orElseThrow(()->new UsernameNotFoundException("Cannot find user " + s));
    }

}
