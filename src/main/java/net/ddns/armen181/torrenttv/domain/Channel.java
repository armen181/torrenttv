package net.ddns.armen181.torrenttv.domain;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.ddns.armen181.torrenttv.util.AccessTranslation;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Column(name = "groupCategory")
    private int groupCategory;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "epgNumber", nullable = false)
    private int epgNumber;

    //@Getter(AccessLevel.NONE)
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "accessTranslation", nullable = false)
    private AccessTranslation accessTranslation;

//    @Getter(AccessLevel.NONE)
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "favourite",
//            joinColumns = @JoinColumn(name ="user"),
//            inverseJoinColumns = @JoinColumn(name ="chennel_id"))
//    private User user;


    public Channel() {
    }

    public Channel(int channelNumber, String name, String logo, int epgNumber, Category category, AccessTranslation accessTranslation,int groupCategory) {
        this.channelNumber = channelNumber;
        this.name = name;
        this.logo = logo;
        this.epgNumber = epgNumber;
        this.category = category;
        this.accessTranslation= accessTranslation;
      this.groupCategory=groupCategory;
    }


}
