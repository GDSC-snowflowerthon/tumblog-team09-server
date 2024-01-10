package snowflake.tumblog.tumble.domain;

import jakarta.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Tumbles {

    private List<Tumble> tumbles = new ArrayList<>();

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
