package snowflake.tumblog.user.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import snowflake.tumblog.user.domain.User;

@Component
public interface UserPort {

    void save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();
}
