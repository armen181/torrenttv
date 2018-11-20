package net.ddns.armen181.torrenttv.domain;


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
@EqualsAndHashCode(exclude = {"user", "category"})
@Entity
@Table(name = "channel")
public class Channel implements Serializable {

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
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;


    @ManyToMany(mappedBy = "channels")
    private Set<User> user = new HashSet<>();

    public Channel() {
    }

//    public Channel(int channelNumber, String name, String logo, int epgNumber, Category category,int groupCategory,AccessTranslation accessTranslation, User user) {
//        this.channelNumber = channelNumber;
//        this.name = name;
//        this.logo = logo;
//        this.epgNumber = epgNumber;
//      //  this.category = category;
//        this.groupCategory =groupCategory;
//        this.accessTranslation=accessTranslation;
//        this.user=user;
//    }

    public Channel(int channelNumber, String name, int groupCategory, String logo, int epgNumber, AccessTranslation accessTranslation) {
        this.channelNumber = channelNumber;
        this.name = name;
        this.groupCategory = groupCategory;
        this.logo = logo;
        this.epgNumber = epgNumber;
        this.accessTranslation = accessTranslation;
    }

//    public Channel(int channelNumber, String name, int groupCategory, String logo, int epgNumber, AccessTranslation accessTranslation, Category category, Set<User> user) {
//        this.channelNumber = channelNumber;
//        this.name = name;
//        this.groupCategory = groupCategory;
//        this.logo = logo;
//        this.epgNumber = epgNumber;
//        this.accessTranslation = accessTranslation;
//        this.category = category;
//        this.user = user;
//    }
}
