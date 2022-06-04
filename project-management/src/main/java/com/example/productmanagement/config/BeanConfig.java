package com.example.productmanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration class for bean instantiation
 */
@Configuration
public class BeanConfig {
    /*Il injectez in mai multe servicii (CustomerServiceImpl). Il creez o singura data la pornirea aplicatiei si doar
    il injectez(includ) in clase.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)//setez SWAGGER_2
                .select()// select() returneaza o instanta a ApiSelectorBuilder, care ofera o cale de a controla endpoint-urile expuse de Swagger.
                .apis(RequestHandlerSelectors.any()) //Putem configura predicate pentru selectarea RequestHandlers
                .paths(PathSelectors.any()) // folosind any() pentru ambele vom face documentatia pentru tot API-ul disponibila prin Swagger.
                .build();
    }
}
