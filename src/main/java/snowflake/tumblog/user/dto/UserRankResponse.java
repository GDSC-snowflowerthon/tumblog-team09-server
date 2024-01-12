package snowflake.tumblog.user.dto;

public record UserRankResponse(int experiencePoint, int rank) {

    public static UserRankResponse from(Integer experiencePoint, int rank) {
        return new UserRankResponse(experiencePoint, rank);
    }
}
