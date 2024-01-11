package snowflake.tumblog.common.s3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import snowflake.tumblog.chat.dto.ChatGptResponse;
import snowflake.tumblog.common.s3.service.ImageService;

import java.io.IOException;

import static snowflake.tumblog.common.constants.RequestURI.image;

@RestController
@RequiredArgsConstructor
@RequestMapping(image)
@Tag(name = "Image", description = "Image API")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    @Operation(summary = "이미지 업로드", description = "MultipartFile을 S3에 업로드하고 OCR API와 chatGPT API를 통해 정제된 데이터를 반환한다.")
    public ChatGptResponse uploadImage(MultipartFile multipartFile) throws IOException {
        ChatGptResponse response = imageService.uploadImage(multipartFile);
        return response;
    }
}
