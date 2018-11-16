package net.ddns.armen181.torrenttv.domain;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "channels")
public class Channels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "channelNumber", nullable = false)
    private int channelId;

      @Column(name = "name", nullable = false)
      private String name;

    @Column(name = "channelGroup", nullable = false)
    private int group;


    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "epgNumber", nullable = false)
    private int epg_id;

    public Channels() {
    }

    public Channels(int channelId, String name, int group, String logo, int epg_id) {
        this.channelId = channelId;
        this.name = name;
        this.group = group;
        this.logo = logo;
        this.epg_id = epg_id;
    }
}
