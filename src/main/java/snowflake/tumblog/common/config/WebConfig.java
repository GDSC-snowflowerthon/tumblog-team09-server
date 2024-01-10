package snowflake.tumblog.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 프톤트 배포 후 수정
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PATCH")
                .allowCredentials(false);
    }
}