package snowflake.tumblog.user.presentation;

import static snowflake.tumblog.common.constants.RequestURI.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snowflake.tumblog.common.BaseResponse;
import snowflake.tumblog.user.dto.CreateUserRequest;
import snowflake.tumblog.user.dto.HomeResponse;
import snowflake.tumblog.user.dto.UpdateUserRequest;
import snowflake.tumblog.user.dto.MyPageResponse;
import snowflake.tumblog.user.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping(user)
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "특정 요청 값을 통해 회원 가입을 진행합니다. 서비스의 확장성을 위해 추가하였습니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "회원 가입에 실패하였습니다."),
    })
    public void signup(@RequestBody CreateUserRequest request) {
        userService.signup(request);
    }

    @PatchMapping("/nickname")
    public void updateNickname(@RequestBody UpdateUserRequest request) {
        userService.changeNickname(request);
    }

    @GetMapping("/mypage/{userId}")
    public BaseResponse<MyPageResponse> myPage(@PathVariable Long userId) {
        return new BaseResponse(userService.myPage(userId));
    }

    @GetMapping("/home/{userId}")
    public BaseResponse<HomeResponse> home(@PathVariable Long userId) {
        return new BaseResponse<>(userService.home(userId));
    }
}

