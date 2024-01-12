package snowflake.tumblog.ocr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class OcrServiceTest {

    @Autowired
    private OcrService ocrService;

    @Test
    void 영수증을_확인한다() {
        // given
        String imageUrl = "https://mblogthumb-phinf.pstatic.net/MjAyMTAyMDNfMzUg/MDAxNjEyMzMyMTA4NDMx.WR8JyzSs5DLFIWu6kBjTGwjXexu1SvDTQ6EnFmOpVaUg.V1EmxzJXsaI6rAXEZTfHvY_tJ6s4us1-CiVD6bqP0zYg.JPEG.peter5659/1612331747638.jpg?type=w800";

        // when & then
        System.out.print(ocrService.checkImage(imageUrl).toString());
    }

}
