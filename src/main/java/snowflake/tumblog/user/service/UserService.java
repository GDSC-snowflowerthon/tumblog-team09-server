package snowflake.tumblog.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserPort;
import snowflake.tumblog.user.dto.UpdateUserRequest;
import snowflake.tumblog.user.dto.CreateUserRequest;
import snowflake.tumblog.user.dto.UserMyPageResponse;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserPort userPort;

    public void signup(CreateUserRequest request) {
        userPort.save(User.from(request));
    }

    @Transactional
    public void changeNickname(UpdateUserRequest request) {
        User user = userPort.findById(request.userId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        user.changeNickname(request);
    }

    public UserMyPageResponse myPage(Long userId) {
        User user = userPort.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        return UserMyPageResponse.from(user);
    }
}
