package snowflake.tumblog.ocr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import snowflake.tumblog.ocr.domain.OcrProperties;
import snowflake.tumblog.ocr.dto.CheckImageRequest;
import snowflake.tumblog.ocr.dto.OcrRequest;
import snowflake.tumblog.ocr.dto.OcrResponse;

@Service
@RequiredArgsConstructor
class OcrService {

    private final OcrProperties ocrProperties;

    public void checkImage(CheckImageRequest request) {

        WebClient webClient = WebClient.builder()
            .baseUrl(ocrProperties.getInvokeUrl())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("X-OCR-SECRET", ocrProperties.getxOcrSecret())
            .build();

        webClient.post()
            .bodyValue(OcrRequest.from(request.imageUrl()))
            .retrieve()
            .bodyToMono(OcrResponse.class)
            .subscribe(response -> {
	System.out.println("OCR 응답: " + response);
            });
    }
}
