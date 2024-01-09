package snowflake.tumblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import snowflake.tumblog.ocr.domain.OcrProperties;

@SpringBootTest
@EnableConfigurationProperties(OcrProperties.class)
class TumblogApplicationTests {

    @Test
    void contextLoads() {
    }

}
