package net.ddns.armen181.torrenttv.domain;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ddns.armen181.torrenttv.util.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Data
@EqualsAndHashCode(exclude = {"favouriteChannels","categories","favouriteNames"})
@Entity
@Table(name = "user")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "eMail", nullable = false)
    private String eMail;

    @Column(name = "firsName", nullable = false)
    private String firsName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "userPassword", nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "userRole", nullable = false)
    private Role role;


    @ManyToMany()
    @JoinTable(name = "favouriteChannels",
            joinColumns = @JoinColumn(name ="user_id"),
            inverseJoinColumns = @JoinColumn(name ="channel_id"))
    private Set<Channel> favouriteChannels = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "userCategory",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Favourite> favouriteNames = new HashSet<>();


    public User addFavouriteChannel(Channel channel) {
        channel.getUsers().add(this);
        this.favouriteChannels.add(channel);
        return this;
    }

    public User addCategory (Category category) {
        category.getUsers().add(this);
        this.categories.add(category);
        return this;
    }

    public User addFavourite (Favourite favourite) {
        favourite.setUser(this);
        this.getFavouriteNames().add(favourite);
        return this;
    }










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
        return getEMail();
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
