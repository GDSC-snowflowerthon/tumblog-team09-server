package snowflake.tumblog.ocr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import snowflake.tumblog.ocr.domain.OcrProperties;
import snowflake.tumblog.ocr.dto.CheckImageRequest;
import snowflake.tumblog.ocr.dto.OcrRequest;
import snowflake.tumblog.ocr.dto.OcrResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class OcrService {

    private final OcrProperties ocrProperties;

    public void checkImage(CheckImageRequest request) {

        WebClient webClient = WebClient.builder()
            .baseUrl(ocrProperties.getInvokeUrl())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("X-OCR-SECRET", ocrProperties.getxOcrSecret())
            .build();

        try {
            OcrResponse response = webClient.post()
	.bodyValue(OcrRequest.from(request.imageUrl()))
	.retrieve()
	.bodyToMono(OcrResponse.class)
	.block();

            normalizeText(response.getOnlyText());
        } catch (WebClientResponseException e) {
            log.error("오류 응답 본문: {}", e.getResponseBodyAsString());
        }
    }

    private void normalizeText(String text) {
        System.out.print(text);
    }
}
