package snowflake.tumblog.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserPort;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserPort userPort;

    public void signup(User user) {
        userPort.save(user);
    }
}
