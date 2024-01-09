package snowflake.tumblog.ocr.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import snowflake.tumblog.ocr.domain.OcrProperties;
import snowflake.tumblog.ocr.dto.CheckImageRequest;

@SpringBootTest
public class OcrServiceTest {

    private OcrService ocrService;

    @BeforeEach
    void setUp() {
        ocrService = new OcrService(new OcrProperties());
    }

    @Test
    void 영수증을_확인한다() {
        // given
        String imageUrl = "https://t1.daumcdn.net/cfile/tistory/99D6B64B5B6AA7AB03";
        CheckImageRequest request = new CheckImageRequest(imageUrl);

        // when & then
        ocrService.checkImage(request);
    }

}
