package snowflake.tumblog.user.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

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

    private class UserService {

        private final UserPort userPort;

        private UserService(UserPort userPort) {
            this.userPort = userPort;
        }

        public void signup(User user) {
            userPort.save(user);
        }
    }

    private record CreateUserRequest(String nickname) {

        private CreateUserRequest {
            Assert.notNull(nickname, "nickname must not be null");
        }
    }

    private static class User {

        private Long id;
        private String nickname;

        private User(CreateUserRequest request) {
            this.nickname = request.nickname();
        }

        public static User from(CreateUserRequest request) {
            return new User(request);
        }

        public User(String nickname) {
            this.nickname = nickname;
        }

        public void assignId(Long sequence) {
            this.id = sequence;
        }

        public Long getId() {
            return id;
        }

        public String getNickname() {
            return nickname;
        }
    }

    private interface UserPort {

        void save(User user);
    }

    private class UserAdapter implements UserPort {

        private final UserRepository userRepository;

        private UserAdapter(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public void save(User user) {
            userRepository.save(user);
        }
    }

    private class UserRepository {

        private Map<Long, User> persistance = new HashMap<>();
        private Long sequence = 0L;

        public void save(User user) {
            user.assignId(sequence++);
            persistance.put(user.getId(), user);
        }

        public List<User> findAll() {
            return List.copyOf(persistance.values());
        }
    }
}