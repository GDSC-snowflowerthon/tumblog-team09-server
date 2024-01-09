package snowflake.tumblog.user.dto;

import org.springframework.util.Assert;

public record UpdateUserRequest(Long userId, String nickname) {

    public UpdateUserRequest {
        Assert.notNull(nickname, "nickname must not be null");
    }
}
