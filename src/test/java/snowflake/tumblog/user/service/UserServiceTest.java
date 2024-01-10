package snowflake.tumblog.user.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import snowflake.tumblog.user.domain.repository.UserRepository;
import snowflake.tumblog.user.dto.UpdateUserRequest;
import snowflake.tumblog.user.dto.CreateUserRequest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void 회원가입() {
        // given
        회원가입_요청();

        // then
        assertThat(userRepository.findAll()).hasSize(1);
    }

    private void 회원가입_요청() {
        String nickname = "nickname";
        CreateUserRequest request = new CreateUserRequest(nickname);

        // when
        userService.signup(request);
    }

    @Test
    void 닉네임_변경() {
        // given
        회원가입_요청();
        String newNickname = 닉네임_변경_요청();

        // then
        assertThat(userRepository.findById(1L).get().getNickname())
            .isEqualTo(newNickname);
    }

    private String 닉네임_변경_요청() {
        long userId = 1L;
        String newNickname = "newNickname";
        UpdateUserRequest request = new UpdateUserRequest(userId, newNickname);

        // when
        userService.changeNickname(request);
        return newNickname;
    }
}