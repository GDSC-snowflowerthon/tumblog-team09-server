package snowflake.tumblog.chat.dto;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import java.util.List;

public record ChatGptRequest(String model, List<ChatMessage> messages, Double temperature) {

    private static String prefixMessage =
        "여기서 커피 이름, 텀블러 할인 여부, 할인 금액 (컵 할인, 텀블러 할인만), 커피 사이즈 (스타벅스 기준으로 Tall이면 S, Grande면 M, Venti면 L), 주문 시각을 Json 형식으로 알려줘."
            + "이 때, Json 형식만 반환해줘. 다른 얘기는 하지마. JSON 형식은 menu(String), isDiscount(boolean), discountPrice, Size, orderedAt 이렇게 5개의 key를 가지고 있어. 절대 다른 대답은 하지말고 Json 값만 반환해줘";

    public static ChatCompletionRequest from(String text) {
        return ChatCompletionRequest.builder()
            .model("gpt-3.5-turbo")
            .messages(List.of(new ChatMessage("user", prefixMessage + text)))
            .temperature(0.7)
            .build();
    }
}