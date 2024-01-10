package snowflake.tumblog.tumble.dto;

import snowflake.tumblog.tumble.domain.Size;

public record TumbleDetailResponse(String menu, Integer discountPrice, Size size) {

}
