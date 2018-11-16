package net.ddns.armen181.torrenttv.Configuration;

import net.ddns.armen181.torrenttv.repository.UserRepository;
import net.ddns.armen181.torrenttv.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private Environment env;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (userRepository.findByName(s).isPresent()) {
            return  userRepository.findByName(s).get();
        } else {

            throw new UsernameNotFoundException("UserCustom is not found.");
        }
    }

}
