package snowflake.tumblog.user.domain;

import snowflake.tumblog.user.dto.CreateUserRequest;

public class User {

    private Long id;
    private String nickname;

    private User(CreateUserRequest request) {
        this.nickname = request.nickname();
    }

    public static User from(CreateUserRequest request) {
        return new User(request);
    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    public void assignId(Long sequence) {
        this.id = sequence;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}
