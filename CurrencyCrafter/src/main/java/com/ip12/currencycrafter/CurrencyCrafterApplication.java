package com.ip12.currencycrafter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(

                title = "Currency Crafter",
                description = "Application for viewing currency exchange rates",
                version = "1.1",
                license = @License(name = "Apache 2.0", url =
                        "https://www.apache.org/licenses/LICENSE-2.0.html")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "test server")
        }

)

@SpringBootApplication
public class CurrencyCrafterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyCrafterApplication.class, args);
    }
}