package snowflake.tumblog.user.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import snowflake.tumblog.tumble.domain.Tumble;
import snowflake.tumblog.tumble.domain.Tumbles;
import snowflake.tumblog.user.domain.User;

public class HomeResponse {

    private int annualSavedMoney;
    private int annualSavedCarbon;
    private List<TumbleResponse> monthlyTumbles;
    private String Level;

    private HomeResponse(int annualSavedMoney, int annualSavedCarbon,
        List<TumbleResponse> monthlyTumbles, String level) {
        this.annualSavedMoney = annualSavedMoney;
        this.annualSavedCarbon = annualSavedCarbon;
        this.monthlyTumbles = monthlyTumbles;
        Level = level;
    }

    public static HomeResponse from(User user) {
        return new HomeResponse(user.getTumbles().calculateAnnualSaving(),
            user.getTumbles().calculateAnnualCarbonSaving(),
            TumbleResponse.from(user.getTumbles()),
            user.getLevel().name());
    }

    public static class TumbleResponse {

        private Long tumbleId;
        private LocalDate createdDate;

        public TumbleResponse(Long tumbleId, LocalDate createdDate) {
            this.tumbleId = tumbleId;
            this.createdDate = createdDate;
        }

        public static List<TumbleResponse> from(Tumbles tumbles) {
            return tumbles.toResponse();
        }
    }
}
