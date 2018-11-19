package net.ddns.armen181.torrenttv.DTO;

import lombok.Data;

@Data
public class ScreenShotDto {
    private int success;
    private PicsDto[] screens;

    public ScreenShotDto(int success, PicsDto[] screens) {
        this.success = success;
        this.screens = screens;
    }
}
