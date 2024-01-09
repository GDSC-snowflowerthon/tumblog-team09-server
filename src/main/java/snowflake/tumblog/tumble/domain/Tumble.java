package snowflake.tumblog.tumble.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snowflake.tumblog.user.domain.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Tumble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tumble_id")
    private User user;

    @Column
    private String menu;
    @Column
    private Integer discountPrice;
    @Column
    private Size size;

    public int getDiscountPrice() {
        return discountPrice;
    }

    public double getCarbon() {
        return size.getCarbon();
    }

    @Builder
    public Tumble(User user, String menu, Integer discountPrice, Size size) {
        this.user = user;
        this.menu = menu;
        this.discountPrice = discountPrice;
        this.size = size;
    }
}
