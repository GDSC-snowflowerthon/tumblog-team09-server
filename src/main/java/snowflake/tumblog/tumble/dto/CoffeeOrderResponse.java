package snowflake.tumblog.tumble.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record CoffeeOrderResponse(String menu,
		  boolean isDiscount,
		  int discountPrice,
		  String size,
		  @JsonFormat(pattern = "yyyy-MM-dd") LocalDate orderedAt) {

}
