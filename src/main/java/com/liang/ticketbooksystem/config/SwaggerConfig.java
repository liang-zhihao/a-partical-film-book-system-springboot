//package com.liang.ticketbooksystem.config;
//
//
//import io.swagger.annotations.ApiOperation;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
////TODO: write new API DOC!
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                // 方法需要有ApiOperation注解才能生存接口文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                // 路径使用any风格
//                .paths(PathSelectors.any())
//                .build()
//                // 如何保护我们的Api，有三种验证（ApiKey, BasicAuth, OAuth）
////                .securitySchemes(security())
//                // 接口文档的基本信息
//                .apiInfo(apiInfo());
//    }
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("Book System").description("web").termsOfServiceUrl("http://www.localhost:8090").version("1.0.0").build();
//    }
//
//
//}
