package ibf2022.batch1.csf.assessment.server.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class EnableCors implements WebMvcConfigurer{
    // final String path;
    // final String origins;
    // public EnableCors(String path, String origins){
    //     this.path = path;
    //     this.origins = origins;
    // }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        // registry.addMapping(path)   
        //     .allowedOrigins(origins);
        registry
        .addMapping("/**")
        .allowedOrigins("*")
        .allowedHeaders("*")
        .allowedMethods("*")
        .exposedHeaders("*")
        .maxAge(3600);
    }
    
}
