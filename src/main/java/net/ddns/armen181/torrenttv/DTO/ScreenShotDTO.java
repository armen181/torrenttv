package net.ddns.armen181.torrenttv.DTO;

import lombok.Data;

@Data
public class ScreenShotDTO {
    private int success;
    private PicsDTO[] screens;

    public ScreenShotDTO(int success, PicsDTO[] screens) {
        this.success = success;
        this.screens = screens;
    }
}
