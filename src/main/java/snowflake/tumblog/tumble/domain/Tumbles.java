package snowflake.tumblog.tumble.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Tumbles {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tumble> tumbles = new ArrayList<>();

    public static Tumbles initial() {
        return new Tumbles();
    }

    public Integer getTotalDiscountPrice() {
        return tumbles.stream()
            .mapToInt(tumble -> tumble.getDiscountPrice())
            .sum();
    }

    public double getTotalSavedCarbon() {
        return tumbles.stream()
            .mapToDouble(tumble -> tumble.getCarbon())
            .sum();
    }

    public Integer size() {
        return tumbles.size();
    }
}
