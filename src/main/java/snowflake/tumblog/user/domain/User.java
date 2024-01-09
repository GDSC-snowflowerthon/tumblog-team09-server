package snowflake.tumblog.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snowflake.tumblog.user.dto.UpdateUserRequest;
import snowflake.tumblog.user.dto.CreateUserRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private Integer experience = 0;

    @Enumerated(EnumType.STRING)
    private Level level;

    private User(String nickname) {
        this.nickname = nickname;
    }

    public static User from(CreateUserRequest request) {
        return new User(request.nickname());
    }

    public String getNickname() {
        return nickname;
    }

    public void changeNickname(UpdateUserRequest request) {
        this.nickname = request.nickname();
    }

    public void addExperience(int experience) {
        this.experience += experience;
    }

    public Level getLevel() {
        return Level.from(experience);
    }
}
