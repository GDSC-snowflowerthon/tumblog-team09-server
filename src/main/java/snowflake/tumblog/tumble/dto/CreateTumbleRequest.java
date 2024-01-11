package snowflake.tumblog.tumble.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public record CreateTumbleRequest(@DateTimeFormat(pattern = "yyyy년 MM월 dd일") LocalDate createdAt, @NotNull String menu, @Positive Integer discountPrice, @NotNull String size) {

}
