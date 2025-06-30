package sn.edu.isep.dbe.DocsEtConfig.config;

import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Demo documentation et configuration")
                        .description("montre comment générer " +
                                "une documentation OPEN API et " +
                                "comment configurer une application avec des profiles et des variables ")
                        .version("1.0")
                        .contact(new Contact()
                                .email("ndiayeamadoumactar3@gmail.com")
                                .name("Amadou Mactar Ndiaye")
                                .url("https://github.com/mactar27")
                        )
                );
    }
}
