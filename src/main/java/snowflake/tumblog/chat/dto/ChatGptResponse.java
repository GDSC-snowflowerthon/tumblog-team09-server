package snowflake.tumblog.chat.dto;

import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import java.util.List;
import java.util.stream.Collectors;

public record ChatGptResponse(List<Message> messages) {

    public static ChatGptResponse of(ChatCompletionResult result) {
        return new ChatGptResponse(
            result.getChoices().stream()
                .map(completionChoice -> new Message(completionChoice.getMessage().getRole(),
                    completionChoice.getMessage().getContent()))
                .collect(Collectors.toList())
        );
    }

    public record Message(String role, String message) {
        public static Message of(ChatMessage chatMessage) {
            return new Message(chatMessage.getRole(), chatMessage.getContent());
        }
    }

    public record Usage(Long promptTokens, Long completionTokens, Long totalTokens) {
        public static Usage of(com.theokanning.openai.Usage usage) {
            return new Usage(usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());
        }
    }
}
