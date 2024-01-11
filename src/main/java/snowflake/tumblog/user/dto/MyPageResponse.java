package snowflake.tumblog.user.dto;

import org.springframework.util.Assert;
import snowflake.tumblog.user.domain.User;

public record MyPageResponse(Long id, String nickname, Integer numberOfTumbles,
	             Integer savedPrice, Double savedCarbon) {

    public MyPageResponse {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(nickname, "nickname must not be null");
    }

    public static MyPageResponse from(User user) {
        return new MyPageResponse(user.getId(), user.getNickname(), user.getTumbles().size(),
            user.getTumbles()
	.getTotalDiscountPrice(),
            user.getTumbles().getTotalSavedCarbon());
    }
}
