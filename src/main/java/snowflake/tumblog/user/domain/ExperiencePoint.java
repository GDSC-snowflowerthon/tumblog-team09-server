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

    public ExperiencePoint add(int experience) {
        return ExperiencePoint.from(this.experience + experience);
    }

    public Integer get() {
        return experience;
    }
}
