package net.ddns.armen181.torrenttv.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
//     @JoinTable(name = "category_list", joinColumns = { @JoinColumn(name = "channel_id") },
//            inverseJoinColumns = { @JoinColumn(name = "categories_id") })

    private List<Channel> channels = new ArrayList<>();

    public Category() {
    }

    public Category(int categoryIdOnApi, String name, int position, int adult) {//, List<Channel> channels) {
        this.categoryIdOnApi = categoryIdOnApi;
        this.name = name;
        this.position = position;
        this.adult = adult;
        //this.channels = channels;
    }
}
