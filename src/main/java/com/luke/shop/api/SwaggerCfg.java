package com.luke.shop.api;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by luke on 2018/3/23.
 */
@EnableWebMvc
@EnableSwagger2
public class SwaggerCfg {
    @Bean
    public Docket docketCfg(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2) ;
        ApiInfo apiInfo = new ApiInfo(
                "api",
                "api",
                "V-test",
                "www.luke.com",
                "llg6yy@163.com",
                "",
                ""
        ) ;
        docket.apiInfo(apiInfo) ;
        return docket ;
    }
}
