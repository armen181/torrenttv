package net.ddns.armen181.torrenttv.domain;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import net.ddns.armen181.torrenttv.util.AccessTranslation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
//    @JoinTable(name = "category_list", joinColumns = { @JoinColumn(name = "categories_id") },
//            inverseJoinColumns = { @JoinColumn(name = "channel_id") })
    private Category category;



      @ManyToMany(mappedBy ="channels" )
      private Set<User> users = new HashSet<>();

    public Channel() {
    }

    public Channel(int channelNumber, String name, String logo, int epgNumber, Category category,int groupCategory,AccessTranslation accessTranslation) {
        this.channelNumber = channelNumber;
        this.name = name;
        this.logo = logo;
        this.epgNumber = epgNumber;
        this.category = category;
        this.groupCategory =groupCategory;
        this.accessTranslation=accessTranslation;
    }
}
