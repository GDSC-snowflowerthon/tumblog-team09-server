package snowflake.tumblog.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserPort;
import snowflake.tumblog.user.dto.CreateUserRequest;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserPort userPort;

    public void signup(CreateUserRequest request) {
        userPort.save(User.from(request));
    }
}
