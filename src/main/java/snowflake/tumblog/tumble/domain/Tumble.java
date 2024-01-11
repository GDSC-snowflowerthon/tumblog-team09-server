package snowflake.tumblog.tumble.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snowflake.tumblog.tumble.dto.CreateTumbleRequest;
import snowflake.tumblog.user.domain.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Tumble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String menu;
    private Integer discountPrice;
    private LocalDate createdAt;


    private Tumble(User user, String menu, Integer discountPrice, LocalDate createdAt, Size size) {
        this.user = user;
        this.menu = menu;
        this.discountPrice = discountPrice;
        this.createdAt = createdAt;
        this.size = size;
    }

    @Enumerated(EnumType.STRING)
    private Size size;

    public int getDiscountPrice() {
        return discountPrice;
    }

    public double getCarbon() {
        return size.getCarbon();
    }


    public static Tumble of(User user, CreateTumbleRequest request) {
        {
            return new Tumble(user, request.menu(), request.discountPrice(), request.createdAt(),
	Size.from(request.size()));
        }
    }
}
