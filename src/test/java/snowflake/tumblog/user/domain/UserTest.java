package snowflake.tumblog.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import snowflake.tumblog.user.domain.repository.UserRepository;
import snowflake.tumblog.user.dto.CreateUserRequest;
import snowflake.tumblog.user.service.UserService;

@SpringBootTest
class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Test
    void 회원가입() {
        // given
        String nickname = "nickname";
        CreateUserRequest request = new CreateUserRequest(nickname);
        User user = User.from(request);

        // when
        userService.signup(user);

        // then
        Assertions.assertThat(userRepository.findAll()).hasSize(1);
    }
}