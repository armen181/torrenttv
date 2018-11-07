package net.ddns.armen181.torrenttv.DTO;

import lombok.Data;

@Data
public class PicsDTO {
    private int id;
    private String filename;

    public PicsDTO(int id, String filename) {
        this.id = id;
        this.filename = filename;
    }
}
