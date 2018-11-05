package net.ddns.armen181.torrenttv.DTO;

import lombok.Data;

@Data
public class TranslationList {
private int success;
private CategoriesDTO[] categories;
private ChannelsDTO[] channels;

    public TranslationList(int success, CategoriesDTO[] categories, ChannelsDTO[] channels) {
        this.success = success;
        this.categories = categories;
        this.channels = channels;
    }
}
