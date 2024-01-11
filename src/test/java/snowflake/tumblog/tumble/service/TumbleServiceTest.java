package snowflake.tumblog.tumble.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import snowflake.tumblog.tumble.domain.Size;
import snowflake.tumblog.tumble.dto.CreateTumbleRequest;
import snowflake.tumblog.tumble.dto.TumbleDetailResponse;
import snowflake.tumblog.tumble.repository.TumbleRepository;
import snowflake.tumblog.user.dto.CreateUserRequest;
import snowflake.tumblog.user.service.UserService;

@SpringBootTest
class TumbleServiceTest {

    @Autowired
    private TumbleService tumbleService;

    @Autowired
    private UserService userService;

    @Autowired
    private TumbleRepository tumbleRepository;

    @BeforeEach
    void setUp() {
        userService.signup(CreateUserRequest.of("nickname"));
        tumbleRepository.flush();
    }

    @Test
    void Tumble_생성() {
        Tumble_생성_요청();

        // then
        assertThat(tumbleRepository.findAll()).hasSize(1);
    }

    private void Tumble_생성_요청() {
        // given
        CreateTumbleRequest request = new CreateTumbleRequest(LocalDate.of(2024, 01, 10), "아메리카노",
            3000, "M");
        Long userId = 1L;

        // when
        tumbleService.create(request, userId);
    }

    @Test
    void Tumble_조회() {
        // given
        Tumble_생성_요청();
        Long tumbleId = 1L;

        // when
        TumbleDetailResponse response = tumbleService.detail(tumbleId);

        // then
        assertThat(response.menu()).isEqualTo("아메리카노");
        assertThat(response.discountPrice()).isEqualTo(3000);
        assertThat(response.size()).isEqualTo(Size.M);
    }
}