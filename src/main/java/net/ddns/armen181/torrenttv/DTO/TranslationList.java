package net.ddns.armen181.torrenttv.DTO;

import lombok.Data;

@Data
public class TranslationList {
private int success;
private CategoryDTO[] categories;
private ChannelsDTO[] channels;

    public TranslationList(int success, CategoryDTO[] categories, ChannelsDTO[] channels) {
        this.success = success;
        this.categories = categories;
        this.channels = channels;
    }
}
