package snowflake.tumblog.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snowflake.tumblog.user.domain.repository.UserAdapter;
import snowflake.tumblog.user.domain.repository.UserPort;
import snowflake.tumblog.user.domain.repository.UserRepository;
import snowflake.tumblog.user.dto.CreateUserRequest;
import snowflake.tumblog.user.service.UserService;

class UserTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        UserPort userPort = new UserAdapter(userRepository);
        userService = new UserService(userPort);
    }

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