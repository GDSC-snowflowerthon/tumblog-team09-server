package snowflake.tumblog.tumble.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import snowflake.tumblog.tumble.domain.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTumbleResponse {
    private String menu;
    private Integer discountPrice;
    private Size size;
}
