package snowflake.tumblog.user.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snowflake.tumblog.common.BaseEntity;
import snowflake.tumblog.tumble.domain.Tumble;
import snowflake.tumblog.tumble.domain.Tumbles;
import snowflake.tumblog.user.dto.UpdateUserRequest;
import snowflake.tumblog.user.dto.CreateUserRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;

    @Embedded
    private ExperiencePoint experiencePoint;

    @Embedded
    private Tumbles tumbles = Tumbles.initial();

    @Enumerated(EnumType.STRING)
    private Level level = Level.LV_1;

    private User(String nickname) {
        this.nickname = nickname;
    }

    public static User from(CreateUserRequest request) {
        return new User(request.nickname());
    }

    public void changeNickname(UpdateUserRequest request) {
        this.nickname = request.nickname();
    }

    public void addExperiencePoint() {
        if (experiencePoint == null) {
            experiencePoint = ExperiencePoint.initial();
        }
        int consecutiveDays = tumbles.getConsecutiveFromToday();
        if (consecutiveDays > 0) {
            experiencePoint = experiencePoint.addBonus(consecutiveDays);
            return;
        }
        this.experiencePoint = experiencePoint.add();
        calculateLevel();
    }

    private void calculateLevel() {
        this.level = Level.from(experiencePoint.get());
    }

    public void addTumble(Tumble tumble) {
        this.tumbles.add(tumble);
        addExperiencePoint();
    }
}
