package snowflake.tumblog.ocr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import snowflake.tumblog.chat.dto.ChatGptResponse;
import snowflake.tumblog.chat.service.ChatService;
import snowflake.tumblog.ocr.domain.OcrProperties;
import snowflake.tumblog.ocr.dto.OcrRequest;
import snowflake.tumblog.ocr.dto.OcrResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class OcrService {

    private final OcrProperties ocrProperties;
    private final ChatService chatService;

    public ChatGptResponse checkImage(String imageUrl) {

        WebClient webClient = WebClient.builder()
            .baseUrl(ocrProperties.getInvokeUrl())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("X-OCR-SECRET", ocrProperties.getxOcrSecret())
            .build();

        try {
            OcrResponse response = webClient.post()
                .bodyValue(OcrRequest.from(imageUrl))
                .retrieve()
                .bodyToMono(OcrResponse.class)
                .block();
            return normalizeText(response.getOnlyText());
        } catch (WebClientResponseException e) {
            log.error("오류 응답 본문: {}", e.getResponseBodyAsString());
        }
        return null;
    }

    private ChatGptResponse normalizeText(String text) {
        ChatGptResponse response = chatService.completion(text);
        System.out.print(response.messages().get(0).message());
        return response;
    }
}
