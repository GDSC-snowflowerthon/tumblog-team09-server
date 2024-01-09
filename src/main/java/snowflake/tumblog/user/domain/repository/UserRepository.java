package snowflake.tumblog.user.domain.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import snowflake.tumblog.user.domain.User;

public class UserRepository {

    private Map<Long, User> persistance = new HashMap<>();
    private Long sequence = 0L;

    public void save(User user) {
        user.assignId(sequence++);
        persistance.put(user.getId(), user);
    }

    public List<User> findAll() {
        return List.copyOf(persistance.values());
    }
}
