package snowflake.tumblog.user.service;

import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserPort;

public class UserService {

    private final UserPort userPort;

    public UserService(UserPort userPort) {
        this.userPort = userPort;
    }

    public void signup(User user) {
        userPort.save(user);
    }
}
