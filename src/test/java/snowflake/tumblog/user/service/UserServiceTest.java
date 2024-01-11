package snowflake.tumblog.user.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import snowflake.tumblog.tumble.dto.CreateTumbleRequest;
import snowflake.tumblog.tumble.repository.TumbleRepository;
import snowflake.tumblog.tumble.service.TumbleService;
import snowflake.tumblog.user.domain.repository.UserRepository;
import snowflake.tumblog.user.dto.HomeResponse;
import snowflake.tumblog.user.dto.UpdateUserRequest;
import snowflake.tumblog.user.dto.CreateUserRequest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TumbleService tumbleService;

    @Autowired
    private TumbleRepository tumbleRepository;

    @BeforeEach
    void setUp() {
        userRepository.flush();
        tumbleRepository.deleteAll();
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

    @Test
    void 홈_조회() {
        // given
        회원가입_요청();

        for (int i = 1; i <= 9; i++) {
            Tumble_생성_요청(i);
        }

        // when
        HomeResponse response = userService.home(1L);

        // then
        assertThat(response.annualSavedCarbon()).isGreaterThan(10000);
        assertThat(response.annualSavedCarbon()).isGreaterThan(10000);
        assertThat(response.monthlyTumbles()).hasSize(9);
    }

    private void Tumble_생성_요청(int day) {
        // given
        CreateTumbleRequest request = new CreateTumbleRequest(LocalDate.of(2024, 01, day), "아메리카노",
            3000, "M");
        Long userId = 1L;

        // when
        tumbleService.create(request, userId);
    }
}