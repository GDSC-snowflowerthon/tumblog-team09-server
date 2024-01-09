package snowflake.tumblog.user.domain.repository;

import snowflake.tumblog.user.domain.User;

public interface UserPort {

    void save(User user);
}
