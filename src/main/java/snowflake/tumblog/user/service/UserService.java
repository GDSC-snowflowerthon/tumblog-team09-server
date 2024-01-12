package snowflake.tumblog.user.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snowflake.tumblog.user.domain.User;
import snowflake.tumblog.user.domain.repository.UserPort;
import snowflake.tumblog.user.dto.UpdateUserRequest;
import snowflake.tumblog.user.dto.CreateUserRequest;
import snowflake.tumblog.user.dto.HomeResponse;
import snowflake.tumblog.user.dto.MyPageResponse;
import snowflake.tumblog.user.dto.UserRankResponse;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserPort userPort;

    public void signup(CreateUserRequest request) {
        userPort.save(User.from(request));
    }

    public void changeNickname(UpdateUserRequest request) {
        User user = userPort.findById(request.userId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        user.changeNickname(request);
    }

    @Transactional(readOnly = true)
    public MyPageResponse myPage(Long userId) {
        User user = userPort.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        return MyPageResponse.from(user);
    }

    @Transactional(readOnly = true)
    public HomeResponse home(Long userId, int year, int month) {
        User user = userPort.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        return HomeResponse.from(user, year, month);
    }

    public UserRankResponse rank(Long userId) {
        User user = userPort.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        List<User> users = userPort.findAll();
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
	return o2.getExperiencePoint().get() - o1.getExperiencePoint().get();
            }
        });

        int rank = 1;
        for (User u : users) {
            if (u.getId().equals(user.getId())) {
	break;
            }
            rank++;
        }
        return UserRankResponse.from(user.getExperiencePoint().get(), rank);
    }
}
