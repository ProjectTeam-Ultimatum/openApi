package ultimatum.project.openapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(title = "Project Team - Ultimatum",
                description = "제주 API DB 저장",
                version = "v1")
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi ApiList(){
        String[] path = {
                "ultimatum.project.openapi.controller"
        };
        return GroupedOpenApi.builder()
                .group("1. api")
                .packagesToScan(path)
                .build();
    }
}

