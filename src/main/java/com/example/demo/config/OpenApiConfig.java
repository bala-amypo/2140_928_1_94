package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        // Production Server
        Server productionServer = new Server();
        productionServer.setUrl("https://9154.408procr.amypo.ai");
        productionServer.setDescription("Production Server");
        
        // Local Development Server
        Server localServer = new Server();
        localServer.setUrl("http://localhost:9001");
        localServer.setDescription("Local Development Server");

        Contact contact = new Contact();
        contact.setName("Bin Overflow Predictor Team");
        contact.setEmail("support@demo.com");

        License mitLicense = new License()
            .name("MIT License")
            .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
            .title("Bin Overflow Predictor API")
            .version("1.0")
            .contact(contact)
            .description("""
                API for managing waste bins, monitoring fill levels, and predicting overflow events.
                
                ## Authentication
                Use `/auth/login` to get a JWT token, then include it in the Authorization header:
                ```
                Authorization: Bearer {token}
                ```
                
                ## Endpoints
                - `/api/bins` - Bin management
                - `/api/fill-records` - Fill level records
                - `/api/models` - Usage pattern models
                - `/api/predictions` - Overflow predictions
                - `/api/zones` - Zone management
                - `/auth/*` - Authentication
                """)
            .license(mitLicense);

        // JWT Security Scheme
        SecurityScheme securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement()
            .addList("bearerAuth");

        return new OpenAPI()
            .info(info)
            .servers(Arrays.asList(productionServer, localServer))
            .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
            .addSecurityItem(securityRequirement);
    }
}