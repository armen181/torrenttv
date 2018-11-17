package net.ddns.armen181.torrenttv.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "channels")
public class Channel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "channelNumber", nullable = false)
    private int channelId;

    @Column(name = "name", nullable = false)
    private String name;

//    @Column(name = "channelGroup", nullable = false)
//    private int group;


    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "epgNumber", nullable = false)
    private int epg_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
//    @JoinTable(name = "category_list", joinColumns = { @JoinColumn(name = "categories_id") },
//            inverseJoinColumns = { @JoinColumn(name = "channel_id") })
    private Category category;

    public Channel() {
    }

    public Channel(int channelId, String name, String logo, int epg_id, Category category) {
        this.channelId = channelId;
        this.name = name;
        this.logo = logo;
        this.epg_id = epg_id;
        this.category = category;
    }
}
