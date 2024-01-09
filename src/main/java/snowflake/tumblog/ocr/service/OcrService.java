package snowflake.tumblog.ocr.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import snowflake.tumblog.ocr.domain.OcrProperties;
import snowflake.tumblog.ocr.dto.CheckImageRequest;
import snowflake.tumblog.ocr.dto.OcrRequest;
import snowflake.tumblog.ocr.dto.OcrResponse;

class OcrService {

    private final OcrProperties ocrProperties;

    public OcrService(OcrProperties ocrProperties) {
        this.ocrProperties = ocrProperties;
    }

    public void checkImage(CheckImageRequest request) {
        String baseUrl = "https://dt2n2lokxq.apigw.ntruss.com/custom/v1/27526/15d70a8654729eb0b119502b99068dc860830e3033cea7c94dd696d27ac472c5/general";
        String xOcrSecret = "T0FvQkdKcU9Yd29mc294cFp4QkZ3d0hNTVpDQWJnUE4=";

        WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("X-OCR-SECRET", xOcrSecret)
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
