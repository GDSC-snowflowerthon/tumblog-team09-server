package snowflake.tumblog.ocr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import snowflake.tumblog.ocr.dto.CheckImageRequest;

@SpringBootTest
public class OcrServiceTest {

    @Autowired
    private OcrService ocrService;

    @Test
    void 영수증을_확인한다() {
        // given
        String imageUrl = "https://t1.daumcdn.net/cfile/tistory/99D6B64B5B6AA7AB03";
        CheckImageRequest request = new CheckImageRequest(imageUrl);

        // when & then
        ocrService.checkImage(request);
    }

}
