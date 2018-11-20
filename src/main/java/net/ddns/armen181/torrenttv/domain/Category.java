package net.ddns.armen181.torrenttv.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@EqualsAndHashCode(exclude = {"channels"})
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "categoryIdOnApi", nullable = false)
    private int categoryIdOnApi;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "position", nullable = false)
    private int position;


    @Column(name = "adult", nullable = false)
    private int adult;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Channel> channels = new HashSet<>();


    public Category() {
    }


    public Category addChannel(Channel channel){
    channel.setCategory(this);
    this.channels.add(channel);
    return this;
}
}
