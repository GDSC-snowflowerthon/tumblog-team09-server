package snowflake.tumblog.tumble.controller;

import static snowflake.tumblog.common.constants.RequestURI.*;
import static snowflake.tumblog.common.enums.BaseResponseStatus.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import snowflake.tumblog.common.BaseException;
import snowflake.tumblog.common.BaseResponse;
import snowflake.tumblog.common.constants.RequestURI;
import snowflake.tumblog.common.enums.BaseResponseStatus;
import snowflake.tumblog.tumble.dto.PostTumbleReq;
import snowflake.tumblog.tumble.service.TumbleService;

@RestController
@RequestMapping(tumble)
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
