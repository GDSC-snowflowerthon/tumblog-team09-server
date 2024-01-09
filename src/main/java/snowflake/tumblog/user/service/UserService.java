package snowflake.tumblog.user.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserPort;
import snowflake.tumblog.user.dto.UpdateUserRequest;
import snowflake.tumblog.user.dto.CreateUserRequest;

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
}
