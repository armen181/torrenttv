package net.ddns.armen181.torrenttv.Configuration;

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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s.equals("admin")) {
            return new UserCustom(env.getProperty("admin.username"), BCrypt.hashpw(env.getProperty("admin.password"), BCrypt.gensalt(12)), Role.ADMIN);
        } else if(s.equals("user"))
            return new UserCustom(env.getProperty("user.username"), BCrypt.hashpw(env.getProperty("user.password"), BCrypt.gensalt(12)), Role.USER);

        else {

            throw new UsernameNotFoundException("UserCustom is not found.");
        }
    }
}
