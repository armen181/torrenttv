package net.ddns.armen181.torrenttv.domain;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

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


    @OneToMany(//cascade = CascadeType.ALL,
            //fetch = FetchType.LAZY,
            mappedBy = "category")
    @Getter(AccessLevel.NONE)
    private Set<Channel> channels = new HashSet<>();


    public Set<Channel> getChannels() {
        return channels;
    }

    public Category() {
    }

    public Category(int categoryIdOnApi, String name, int position, int adult) {
        this.categoryIdOnApi = categoryIdOnApi;
        this.name = name;
        this.position = position;
        this.adult = adult;
    }
}
