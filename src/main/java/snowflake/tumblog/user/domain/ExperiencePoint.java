package snowflake.tumblog.user.domain;

import static snowflake.tumblog.common.constants.Constant.ZERO;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExperiencePoint {

    private Integer experience;

    private ExperiencePoint(Integer experience) {
        this.experience = experience;
    }

    public static ExperiencePoint initial() {
        return new ExperiencePoint(ZERO);
    }

    public static ExperiencePoint from(int experience) {
        return new ExperiencePoint(experience);
    }

    public ExperiencePoint add() {
        return ExperiencePoint.from(this.experience + 10);
    }

    public ExperiencePoint addBonus(int consecutiveDays) {
        return ExperiencePoint.from(
            this.experience + 10 + getBonusPoint(consecutiveDays));
    }

    public int getBonusPoint(int consecutiveDays) {
        int baseExperience = 10; // 기본 경험치
        int additionalExperience = 0; // 추가 경험치

        if (consecutiveDays > 0) {
            additionalExperience = Math.min(consecutiveDays, 5) * 2; // 연속 일수가 5일까지는 일수당 2점씩 추가
            if (consecutiveDays >= 7) {
	additionalExperience += 10; // 7일 연속 기록 시 추가 보너스
            }
        }

        return baseExperience + additionalExperience;
    }


    public Integer get() {
        return experience;
    }
}
