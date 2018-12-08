package net.ddns.armen181.torrenttv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.ddns.armen181.torrenttv.util.AccessTranslation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@EqualsAndHashCode(exclude = {"users", "category"})
@Entity
@Table(name = "channel")
public class Channel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "channelNumber", nullable = false)
    private int channelNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "groupCategory", nullable = false)
    private int groupCategory;


    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "epgNumber", nullable = false)
    private int epgNumber;

    @Enumerated(EnumType.STRING)
    private AccessTranslation accessTranslation;

    @Getter(AccessLevel.NONE)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @JsonIgnore
    @ManyToMany(mappedBy = "favouriteChannels",fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    public Channel() {
    }

    public Channel(int channelNumber, String name, int groupCategory, String logo, int epgNumber, AccessTranslation accessTranslation) {
        this.channelNumber = channelNumber;
        this.name = name;
        this.groupCategory = groupCategory;
        this.logo = logo;
        this.epgNumber = epgNumber;
        this.accessTranslation = accessTranslation;
    }


}
