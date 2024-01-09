package snowflake.tumblog.ocr.dto;

import java.time.Instant;
import java.util.List;

public record OcrRequest(List<ImageData> images, String lang, String requestId,
	         String resultType, Long timestamp, String version) {

    public static OcrRequest from(String imageUrl) {
        ImageData imageData = new ImageData("png", "medium", null, imageUrl);
        return new OcrRequest(List.of(imageData), "ko", "string", "string",
            System.currentTimeMillis(), "V1");
    }

    private record ImageData(String format, String name, String data, String url) {

    }
}
