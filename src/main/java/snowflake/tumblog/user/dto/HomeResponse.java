package snowflake.tumblog.user.dto;

import java.time.LocalDate;
import java.util.List;
import snowflake.tumblog.tumble.domain.Tumbles;
import snowflake.tumblog.user.domain.User;

public record HomeResponse(int annualSavedMoney, int annualSavedCarbon,
	           List<TumbleResponse> monthlyTumbles, int Level) {

    public static HomeResponse from(User user, int year, int month) {
        return new HomeResponse(
            user.getTumbles().calculateAnnualSaving(),
            user.getTumbles().calculateAnnualCarbonSaving(),
            TumbleResponse.from(user.getTumbles(),year ,month),
            user.getLevel().getDescription()
        );
    }

    public record TumbleResponse(Long tumbleId, LocalDate createdDate) {

        public static List<TumbleResponse> from(Tumbles tumbles,int year, int month) {
            return tumbles.toResponse(year, month);
        }
    }
}
