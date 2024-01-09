package snowflake.tumblog.user.presentation;

import static snowflake.tumblog.common.Constant.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snowflake.tumblog.common.BaseResponse;
import snowflake.tumblog.user.dto.CreateUserRequest;
import snowflake.tumblog.user.dto.UpdateUserRequest;
import snowflake.tumblog.user.dto.UserMyPageResponse;
import snowflake.tumblog.user.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping(userURI)
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody CreateUserRequest request) {
        userService.signup(request);
    }

    @PatchMapping("/nickname")
    public void updateNickname(@RequestBody UpdateUserRequest request) {
        userService.changeNickname(request);
    }

    @GetMapping("/mypage/{userId}")
    public BaseResponse<UserMyPageResponse> myPage(@PathVariable Long userId) {
        return new BaseResponse(userService.myPage(userId));
    }
}

