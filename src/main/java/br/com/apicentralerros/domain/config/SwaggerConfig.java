package br.com.apicentralerros.domain.config;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {



//    @Value("${security.oauth2.client.client-id}")
//    String clientId;
//    @Value("${security.oauth2.client.client-secret}")
//    String clientSecret;
//
//    @Value("${config.oauth2.accessTokenUri}")
//    String oAuthServerUri;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.br.centraerros"))
                .build()
                .securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
                .securityContexts(Arrays.asList(securityContext()));
    }



//
//    private Predicate<String> postPaths() {
//        return regex("/.*");
//    }
//
//    private Predicate<String> springBootActuatorJmxPaths() {
//        return regex("^/(?!env|restart|pause|resume|refresh).*$");
//    }
//
//
//
//    @Bean
//    List<GrantType> grantTypes() {
//        List<GrantType> grantTypes = new ArrayList<>();
//        TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(oAuthServerUri+"/oauth/authorize", clientId, clientSecret );
//        TokenEndpoint tokenEndpoint = new TokenEndpoint(oAuthServerUri+"/oauth/token", "token");
//        grantTypes.add(new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint));
//        return grantTypes;
//    }
//
//    @Bean
//    SecurityScheme oauth() {
//        return new OAuthBuilder()
//                .name("OAuth2")
//                .scopes(scopes())
//                .grantTypes(grantTypes())
//                .build();
//    }
//
//    private List<AuthorizationScope> scopes() {
//        List<AuthorizationScope> list = new ArrayList();
//        list.add(new AuthorizationScope("read_scope","Grants read access"));
//        list.add(new AuthorizationScope("write_scope","Grants write access"));
//        list.add(new AuthorizationScope("admin_scope","Grants read write and delete access"));
//        return list;
//    }
//
//    @Bean
//    public SecurityConfiguration securityInfo() {
//        return new SecurityConfiguration(clientId, clientSecret, "realm", clientId, "apiKey", ApiKeyVehicle.HEADER, "api_key", "");
//    }


    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("ADMIN", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("Token Access", authorizationScopes));
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/event/**"))
                .build();
    }



    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Central de Erros REST API ")
                .description("\"API REST Central de Erros\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add( new PageableHandlerMethodArgumentResolver());
    }



}
