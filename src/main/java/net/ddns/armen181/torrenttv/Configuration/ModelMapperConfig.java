//package net.ddns.armen181.torrenttv.Configuration;
//
//import net.ddns.armen181.torrenttv.DTO.CategoryDTO;
//import net.ddns.armen181.torrenttv.domain.Category;
//import org.modelmapper.ModelMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ModelMapperConfig {
//
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.createTypeMap(CategoryDTO.class,Category.class)
//                .addMapping(CategoryDTO::getId, Category::setCategoryIdOnApi);
//        return modelMapper;
//    }
//}
