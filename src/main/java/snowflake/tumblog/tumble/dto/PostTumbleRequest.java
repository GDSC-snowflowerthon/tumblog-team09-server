package snowflake.tumblog.tumble.dto;

import lombok.Data;
import snowflake.tumblog.tumble.domain.Size;

@Data
public class PostTumbleRequest {
    private String menu;
    private Integer discountPrice;
    private Size size;
}
