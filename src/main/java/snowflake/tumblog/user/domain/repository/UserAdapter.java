package snowflake.tumblog.user.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import snowflake.tumblog.user.domain.User;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserPort {

    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
