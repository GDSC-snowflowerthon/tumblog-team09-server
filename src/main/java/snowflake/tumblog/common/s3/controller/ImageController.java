package snowflake.tumblog.common.s3.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import snowflake.tumblog.chat.dto.ChatGptResponse;
import snowflake.tumblog.common.BaseResponse;
import snowflake.tumblog.common.s3.service.ImageService;

import java.io.IOException;

import static snowflake.tumblog.common.constants.RequestURI.image;

@RestController
@RequiredArgsConstructor
@RequestMapping(image)
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ChatGptResponse uploadImage(MultipartFile multipartFile) throws IOException {
        ChatGptResponse response = imageService.uploadImage(multipartFile);
        return response;
    }
}
