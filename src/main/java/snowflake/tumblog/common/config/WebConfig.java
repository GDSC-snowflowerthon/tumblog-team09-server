package snowflake.tumblog.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "https://tumblog-team09-web.vercel.app")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PATCH")
                .allowCredentials(true);
    }
}