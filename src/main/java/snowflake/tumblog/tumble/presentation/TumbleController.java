package snowflake.tumblog.tumble.controller;

import static snowflake.tumblog.common.constants.RequestURI.*;
import static snowflake.tumblog.common.enums.BaseResponseStatus.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import snowflake.tumblog.common.BaseException;
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
    @PostMapping("/{userId}")
    public BaseResponse<String> createTumble(@RequestBody CreateTumbleRequest request,
        @PathVariable Long userId) {
        try {
            tumbleService.create(request, userId);
            return new BaseResponse<>(SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * [GET] 텀블 상세 조회
     */
    @ResponseBody
    @GetMapping("/{tumbleId}")
    public BaseResponse<GetTumbleResponse> getDessert(@PathVariable("tumbleId") Long tumbleId) {
        try {
            return new BaseResponse<>(tumbleService.getTumble(tumbleId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}