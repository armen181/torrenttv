package net.ddns.armen181.torrenttv.domain;


import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"user"})
@Entity
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Getter(AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Favourite() {
    }
}
