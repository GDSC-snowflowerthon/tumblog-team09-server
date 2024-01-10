package snowflake.tumblog.chat.infrastructure;

import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import snowflake.tumblog.chat.domain.ChatProperties;

@RequiredArgsConstructor
@Configuration
public class ChatConfig {

    private final ChatProperties chatProperties;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(chatProperties.getToken(), Duration.ofSeconds(60));
    }
}