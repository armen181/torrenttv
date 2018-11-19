package net.ddns.armen181.torrenttv.DTO;

import lombok.Data;

@Data
public class TranslationListDto {
private int success;
private CategoryDto[] categories;
private ChannelDto[] channels;

    public TranslationListDto(int success, CategoryDto[] categories, ChannelDto[] channels) {
        this.success = success;
        this.categories = categories;
        this.channels = channels;
    }
}
