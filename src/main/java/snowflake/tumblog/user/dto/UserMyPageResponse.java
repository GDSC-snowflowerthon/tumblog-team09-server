package snowflake.tumblog.user.dto;

import org.springframework.util.Assert;
import snowflake.tumblog.user.domain.User;

public record UserMyPageResponse(Long id, String nickname) {

    public UserMyPageResponse {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(nickname, "nickname must not be null");
    }

    public static UserMyPageResponse from(User user) {
        return new UserMyPageResponse(user.getId(), user.getNickname());
    }
}
