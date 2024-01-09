package snowflake.tumblog.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snowflake.tumblog.global.constants.RequestURI;
import snowflake.tumblog.user.dto.CreateUserRequest;
import snowflake.tumblog.user.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping(RequestURI.userURI)
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody CreateUserRequest request) {
        userService.signup(request);
        return;
    }
}

