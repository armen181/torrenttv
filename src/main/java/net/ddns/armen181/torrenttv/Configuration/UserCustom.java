//package net.ddns.armen181.torrenttv.Configuration;
//
//import lombok.Data;
//import net.ddns.armen181.torrenttv.util.Role;
//import com.google.common.collect.Lists;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//@Data
//public class UserCustom implements UserDetails {
//
//    private String name;
//
//    private String pass;
//
//    private Role role;
//
//    public UserCustom(String name, String pass, Role role) {
//        this.name = name;
//        this.pass = pass;
//        this.role = role;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Lists.newArrayList(new SimpleGrantedAuthority("ROLE_" + role.name()));
//    }
//
//    @Override
//    public String getPassword() {
//        return getPass();
//    }
//
//    @Override
//    public String getUsername() {
//        return getName();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
