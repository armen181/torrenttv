package net.ddns.armen181.torrenttv.domain;

import com.google.common.collect.Lists;
import lombok.Data;
import net.ddns.armen181.torrenttv.util.Role;
import net.ddns.armen181.torrenttv.util.UserAccess;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName", nullable = false)
    private String name;

    @Column(name = "userPassword", nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "userRole", nullable = false)
    private Role role;


    @ManyToMany()
    @JoinTable(name = "favourite",
            joinColumns = @JoinColumn(name ="user_id"),
            inverseJoinColumns = @JoinColumn(name ="channel_id"))
    private Set<Channel> channels = new HashSet<>();

    public User() {
    }

//    public User(String userName, String userPassword, Role role, Set<Channel> channels) {
//        this.name = userName;
//        this.userPassword = userPassword;
//        this.role = role;
//        this.channels = channels;
//    }


//    public User addChannel(Channel channel){
//        channel.setUser(this);
//        this.channels.add(channel);
//        return this;
//    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Lists.newArrayList(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    @Override
    public String getPassword() {
        return getUserPassword();
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
