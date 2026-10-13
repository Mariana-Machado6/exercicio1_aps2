package insper.edu.br.campeonato.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Campeonato",
                version = "1.0.0",
                description = "API para gestão de campeonatos, times, jogadores e partidas"
        )
)
public class OpenApiConfig {
}