package snowflake.tumblog.ocr.presentation;

import static snowflake.tumblog.common.constants.RequestURI.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snowflake.tumblog.chat.dto.ChatGptResponse;
import snowflake.tumblog.ocr.dto.CheckImageRequest;
import snowflake.tumblog.ocr.service.OcrService;

@RequestMapping(ocr)
@RequiredArgsConstructor
@RestController
public class OcrController {

    private final OcrService ocrService;

    @PostMapping("/image")
    public ChatGptResponse image(@RequestBody CheckImageRequest request) {
        ChatGptResponse response = ocrService.checkImage(request);
        return response;
    }
}
