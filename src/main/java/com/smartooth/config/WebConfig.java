<<<<<<< HEAD:src/main/java/com/example/SmartoothAI/config/WebConfig.java
package com.example.SmartoothAI.config;
=======
package com.smartooth.config;
>>>>>>> 9260b36118e8709a00443ddecef833e017043ab9:src/main/java/com/smartooth/config/WebConfig.java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8081")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}

