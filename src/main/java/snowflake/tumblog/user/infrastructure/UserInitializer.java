package snowflake.tumblog.user.infrastructure;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import snowflake.tumblog.tumble.domain.Tumbles;
import snowflake.tumblog.user.domain.ExperiencePoint;
import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class UserInitializer {

    private final UserRepository userRepository;

    @PostConstruct
    public void initialize() {
        userRepository.save(User.initialize("GDSC", ExperiencePoint.from(1220), Tumbles.initial()));

        for (int i = 0; i < 400; i++) {
            userRepository.save(
	User.initialize("test" + i, ExperiencePoint.from(10 * i), Tumbles.initial()));
        }
    }

}
