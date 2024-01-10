package snowflake.tumblog.tumble.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import snowflake.tumblog.common.BaseException;
import snowflake.tumblog.tumble.dto.CreateTumbleRequest;
import snowflake.tumblog.tumble.repository.TumbleRepository;
import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserRepository;
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
    }

    @Test
    void Tumble을_생성한다() throws BaseException {
        // given
        CreateTumbleRequest request = new CreateTumbleRequest(LocalDate.of(2024, 01, 10), "아메리카노",
            3000, "M");
        Long userId = 1L;

        // when
        tumbleService.create(request, userId);

        // then
        Assertions.assertThat(tumbleRepository.findAll()).hasSize(1);
    }
}