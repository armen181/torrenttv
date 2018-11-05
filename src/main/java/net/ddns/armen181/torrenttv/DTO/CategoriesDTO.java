package net.ddns.armen181.torrenttv.DTO;

import lombok.Data;

@Data
public class CategoriesDTO {
    private int id;
    private String name;
    private int position;
    private int adult;

    public CategoriesDTO(int id, String name, int position, int adult) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.adult = adult;
    }
}
