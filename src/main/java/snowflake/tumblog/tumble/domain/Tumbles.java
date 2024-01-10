package snowflake.tumblog.tumble.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import snowflake.tumblog.user.dto.HomeResponse.TumbleResponse;

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

    public void add(Tumble tumble) {
        tumbles.add(tumble);
    }

    public int getConsecutiveFromToday() {
        if (tumbles.isEmpty()) {
            return 0;
        }

        List<LocalDate> sortedDates = tumbles.stream()
            .map(Tumble::getCreatedDate) // Tumble에서 날짜 추출
            .sorted(Comparator.reverseOrder()) // 최신 날짜부터 정렬
            .collect(Collectors.toList());

        int consecutiveDays = 0;
        LocalDate currentDate = LocalDate.now();

        for (LocalDate date : sortedDates) {
            if (date.equals(currentDate.minusDays(consecutiveDays))) {
	consecutiveDays++;
            } else {
	break;
            }
        }
        return consecutiveDays;
    }

    public int calculateAnnualSaving() {
        double totalSaved = getTotalDiscountPrice();
        long daysSinceFirstTumble = ChronoUnit.DAYS.between(getFirstTumbleDate(), LocalDate.now());
        double dailySaving = daysSinceFirstTumble > 0 ? totalSaved / daysSinceFirstTumble : 0;
        return (int) dailySaving * 365; // 연간 절약 금액
    }

    public int calculateAnnualCarbonSaving() {
        double totalCarbonSaved = getTotalSavedCarbon();
        long daysSinceFirstTumble = ChronoUnit.DAYS.between(getFirstTumbleDate(), LocalDate.now());
        double dailyCarbonSaving =
            daysSinceFirstTumble > 0 ? totalCarbonSaved / daysSinceFirstTumble : 0;
        return (int) dailyCarbonSaving * 365; // 연간 탄소 절감량
    }

    private LocalDate getFirstTumbleDate() {
        return tumbles.stream()
            .map(Tumble::getCreatedDate)
            .min(LocalDate::compareTo)
            .orElse(LocalDate.now());
    }

    public List<TumbleResponse> toResponse() {
        return tumbles.stream()
            .map(tumble -> new TumbleResponse(tumble.getId(), tumble.getCreatedDate()))
            .collect(Collectors.toList());
    }
}
