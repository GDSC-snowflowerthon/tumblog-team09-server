package snowflake.tumblog.user.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snowflake.tumblog.common.BaseEntity;
import snowflake.tumblog.user.dto.UpdateUserRequest;
import snowflake.tumblog.user.dto.CreateUserRequest;
import tumble.domain.Tumble;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tumble> tumbles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Level level;

    private User(String nickname) {
        this.nickname = nickname;
        this.experiencePoint = ExperiencePoint.initial();
    }

    public static User from(CreateUserRequest request) {
        return new User(request.nickname());
    }

    public void changeNickname(UpdateUserRequest request) {
        this.nickname = request.nickname();
    }

    public void addExperiencePoint(int experience) {
        experiencePoint = experiencePoint.add(experience);
    }

    public Level calculateLevel() {
        return Level.from(experiencePoint.get());
    }

    public int getSavedPrice() {
        return tumbles.stream()
            .mapToInt(tumble -> tumble.getDiscountPrice())
            .sum();
    }

    public double getSavedCarbon() {
        return tumbles.stream()
            .mapToDouble(tumble -> tumble.getCarbon())
            .sum();
    }

    public Integer getTumblesSize() {
        return tumbles.size();
    }
}
