package snowflake.tumblog.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import snowflake.tumblog.user.dto.CreateUserRequest;

public class UserTest {

    @Test
    void 경험치가_쌓이면_레벨업() {
        // given
        int experience = 100;
        User user = User.from(CreateUserRequest.of("test"));
        user.addExperiencePoint(experience);

        // when & then
        Assertions.assertThat(user.calculateLevel()).isEqualTo(Level.LV_2);
    }
}
