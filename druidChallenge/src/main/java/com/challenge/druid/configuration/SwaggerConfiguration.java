package com.challenge.druid.configuration;

import com.challenge.druid.util.validator.SwaggerConstants;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile(value = SwaggerConstants.PROFILE)
public class SwaggerConfiguration {

  private ApiKey apiKey(){
    return new ApiKey(SwaggerConstants.JWT, SwaggerConstants.AUTHORIZATION_HEADER, SwaggerConstants.HEADER);
  }

  @Bean
  public Docket swaggerApi(TypeResolver typeResolver) {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(SwaggerConstants.PROJECT_CONTROLLER_PATH))
            .paths(PathSelectors.any())
            .build()
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(Arrays.asList(apiKey()))
            .apiInfo(this.apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title(SwaggerConstants.PROJECT_TITLE)
            .description(SwaggerConstants.PROJECT_DESCRIPTION)
            .version(SwaggerConstants.PROJECT_API_VERSION)
            .contact(this.createSwaggerContact())
            .build();
  }

  private SecurityContext securityContext(){
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
  }

  private List<SecurityReference> defaultAuth(){
    AuthorizationScope authorizationScope = new AuthorizationScope(SwaggerConstants.AUTHORIZATION_SCOPE, SwaggerConstants.AUTHORIZATION_DESCRIPTION);
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference(SwaggerConstants.JWT, authorizationScopes));
  }

  private Contact createSwaggerContact() {
    return new Contact(SwaggerConstants.PROJECT_CONTACT_NAME, SwaggerConstants.CONTACT_URL, SwaggerConstants.CONTACT_EMAIL);
  }

}
