package snowflake.tumblog.tumble.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record CoffeeOrderResponse(String menu,
		  boolean isDiscount,
		  int discountPrice,
		  String Size,
		  @JsonFormat(pattern = "yyyy/MM/dd HH:mm") LocalDateTime orderedAt) {

}
