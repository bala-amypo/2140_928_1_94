package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        // Production Server (your URL)
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
            .description("API for managing waste bins, monitoring fill levels, and predicting overflow events")
            .license(mitLicense);

        return new OpenAPI()
            .info(info)
            .servers(Arrays.asList(productionServer, localServer));
    }
}