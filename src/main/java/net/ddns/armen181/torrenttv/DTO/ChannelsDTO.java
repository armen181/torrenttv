package net.ddns.armen181.torrenttv.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChannelsDTO {
    private int id;
    private String name;
    private int group;
    private String logo;
    @JsonProperty("epg_id")
    private int epg_id;

    public ChannelsDTO(int id, String name, int group, String logo, int epg_id) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.logo = logo;
        this.epg_id = epg_id;
    }
}
