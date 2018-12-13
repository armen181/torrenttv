package net.ddns.armen181.torrenttv.controller;

import lombok.NonNull;
import net.ddns.armen181.torrenttv.DTO.CategoryDto;
import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.service.ChannelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static java.lang.Math.toIntExact;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest")
public class CategoryController {


    private final ChannelService channelService;

    public CategoryController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping({"/category"})
    public Set<Channel> getCategory(@NonNull @RequestHeader Integer group){
        return channelService.getChannelsByCategory(group);
    }

    @GetMapping({"/getCategory"})
    public List<CategoryDto> getAllCategory(){
        List<CategoryDto> categoryDtos = new ArrayList<>();
        CategoryDto categoryDto = new CategoryDto(0, "All",0,0);
        categoryDtos.add(categoryDto);
        channelService.getCategory().forEach(x->{
            CategoryDto category = new CategoryDto(toIntExact(x.getId()),x.getName(),x.getPosition(),x.getAdult());
            categoryDtos.add(category);
        });
        return categoryDtos;
    }

}
