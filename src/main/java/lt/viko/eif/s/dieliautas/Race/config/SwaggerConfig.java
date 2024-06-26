package lt.viko.eif.s.dieliautas.Race.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * „Swagger“ konfigūracijos klasė, skirta apibrėžti „OpenAPI“ specifikacijas.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Konfigūruoja ir pateikia pasirinktines „OpenAPI“ specifikacijas.
     *
     * @return „OpenAPI“ objektas, apibrėžiantis API informaciją.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Race Service API")
                        .version("1.0")
                        .description("API dokumentacija Race Service")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
