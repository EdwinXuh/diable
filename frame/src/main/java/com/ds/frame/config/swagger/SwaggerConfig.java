package com.ds.frame.config.swagger;


import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * @author raptor
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //多组配置
//    @Bean
//    public Docket docket1(Environment environment) {
//        //获取环境
//        Profiles profiles=Profiles.of("dev","test");
//        boolean b = environment.acceptsProfiles(profiles);
////        System.out.println(b);
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//                .enable(b)
//                .groupName("第二组")
//                .select()
//                // 为当前包路径
//                .apis(RequestHandlerSelectors.basePackage("com.hdu.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }

    @Value("${swagger.host}")
    private String host;

    @Value("${server.port}")
    private String port;

    @Value("${swagger.enable}")
    private Boolean enable;

    @Value("${dr.name}")
    private String name;

    @Value("${dr.version}")
    private String version;


    @Bean
    public Docket docket(Environment environment) {
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(enable)
                .host(host + ":" + port)
                .groupName("base")
                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.ds"))
                .paths(PathSelectors.ant("/base/**"))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .pathMapping("/");
//                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置标题
                .title("残疾报告动态管理系统")
                // 描述
                .description("残疾报告动态管理系统api")
                // 作者信息
                .contact(new Contact("raptor", "#", "knightcwh@163.com"))
                // 版本
                .version("版本号:" + version)
                .termsOfServiceUrl("#")
                .build();

    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<SecurityScheme>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    @Bean
    public Docket web_api_prdt() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enable)
                .host(host + ":" + port)
                .groupName("wechat")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dr"))
                .paths(PathSelectors.ant("/wechat/**"))
                .build()
                .pathMapping("/");
    }

}
