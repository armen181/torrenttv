package net.ddns.armen181.torrenttv.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TranslationListDto {
private int success;
private List<CategoryDto> categories;
private List<ChannelDto> channels;

    public TranslationListDto(int success, List<CategoryDto> categories, List<ChannelDto> channels) {
        this.success = success;
        this.categories = categories;
        this.channels = channels;
    }
}
