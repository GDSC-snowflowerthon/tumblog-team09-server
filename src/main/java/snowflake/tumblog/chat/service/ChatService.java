package snowflake.tumblog.chat.service;

import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snowflake.tumblog.chat.dto.ChatGptRequest;
import snowflake.tumblog.chat.dto.ChatGptResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

    private final OpenAiService openAiService;

    @Transactional
    public ChatGptResponse completion(String text) {
        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(
            ChatGptRequest.from(text));

        return ChatGptResponse.of(chatCompletion);
    }
}