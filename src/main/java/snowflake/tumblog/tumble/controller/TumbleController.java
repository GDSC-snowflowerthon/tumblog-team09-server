package snowflake.tumblog.tumble.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import snowflake.tumblog.common.BaseException;
import snowflake.tumblog.common.BaseResponse;
import snowflake.tumblog.tumble.dto.PostTumbleReq;
import snowflake.tumblog.tumble.service.TumbleService;

import static snowflake.tumblog.common.BaseResponseStatus.SUCCESS;
import static snowflake.tumblog.common.Constant.tumbleURI;

@RestController
@RequestMapping(tumbleURI)
@RequiredArgsConstructor
public class TumbleController {
    private final TumbleService tumbleService;

    /**
     * [POST] 텀블 등록
     */
    @ResponseBody
    @PostMapping("/{userId}")
    public BaseResponse<String> addTumble(@RequestBody PostTumbleReq postTumbleReq, Long userId) {
        try {
            tumbleService.addTumble(postTumbleReq, userId);
            return new BaseResponse<>(SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
