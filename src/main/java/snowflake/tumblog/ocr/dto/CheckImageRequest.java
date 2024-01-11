package snowflake.tumblog.ocr.dto;

import org.springframework.util.Assert;

public record CheckImageRequest(String imageUrl) {

    public CheckImageRequest {
        Assert.notNull(imageUrl, "imageUrl must not be null");
    }
}
