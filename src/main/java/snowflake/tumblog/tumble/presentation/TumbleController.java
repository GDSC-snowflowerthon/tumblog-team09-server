package snowflake.tumblog.tumble.presentation;

import static snowflake.tumblog.common.constants.RequestURI.*;
import static snowflake.tumblog.common.enums.BaseResponseStatus.*;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import snowflake.tumblog.common.BaseResponse;
import snowflake.tumblog.tumble.dto.TumbleDetailResponse;
import snowflake.tumblog.tumble.dto.CreateTumbleRequest;
import snowflake.tumblog.tumble.service.TumbleService;

@RestController
@RequestMapping(tumble)
@RequiredArgsConstructor
public class TumbleController {

    private final TumbleService tumbleService;

    @ResponseBody
    @PostMapping("")
    public BaseResponse<String> createTumble(@RequestBody CreateTumbleRequest request) {
        tumbleService.create(request);
        return new BaseResponse<>(SUCCESS);
    }

    @ResponseBody
    @GetMapping("/{userId}/{createdAt}")
    public BaseResponse<TumbleDetailResponse> getTumbleDetails(
        @PathVariable("userId") Long userId,
        @PathVariable("createdAt") LocalDate createdAt) {
        return new BaseResponse<>(tumbleService.detail(userId, createdAt));
    }
}
