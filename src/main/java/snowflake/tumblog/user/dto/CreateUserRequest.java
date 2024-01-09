package snowflake.tumblog.user.dto;

import org.springframework.util.Assert;

public record CreateUserRequest(String nickname) {

    public CreateUserRequest {
        Assert.notNull(nickname, "nickname must not be null");
    }
}
