package br.com.example.configuration.openApi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class OpenApiConfig {

    private final OpenApiPropertiesConfig environment;


    @Bean
    public OpenAPI customOpenAPI() {

          return new OpenAPI()
                  .components(new Components())
                  .info(new Info()
                  .title("Usuários")
                  .description("API responsável por gerenciar as informações de Usuários")
                  .version(environment.getAppVersion()))
                  .tags(List.of(new Tag().name("Usuários").description("Endpoints responsáveis por gerenciar as informações de usuários")
                  )
          );
    }
}