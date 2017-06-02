package com.czm.core.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by chen zhan mei
 * on 2017/2/15.
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /***
     *默认请求地址 http://127.0.0.1:8080/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("框架学习")
                .description("项目简介")
                .contact(new Contact("chen zhan mei ", " ", "chenzhanmeixx@163.com"))
                .license("个人网站")
                .licenseUrl("http://www.baidu.com")
                .version("2.0")
                .build();
    }

    @Bean
    public Docket categoryApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.host("127.0.0.1:8081/a")//改变请求地址
                .groupName("test group")
                .apiInfo(apiInfo())
                .select()
                .paths(petstorePaths())
                .build()
                .ignoredParameterTypes(ApiIgnore.class)
                .enableUrlTemplating(true);
    }

    /*用于控制哪些接口对外暴露这个就是以为 get头的 */
    private Predicate<String> petstorePaths() {
        return or(
                regex("/home.*"),
                regex("/table.*"),
                regex("/api/user.*"),
                regex("/api/store.*")
        );
    }

}
