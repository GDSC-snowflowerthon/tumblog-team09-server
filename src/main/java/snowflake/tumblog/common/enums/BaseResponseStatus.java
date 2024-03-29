package snowflake.tumblog.common.enums;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    /**
     * 1000: 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000: Request 오류
     */
    // user(2000~2099)
    INVALID_USER_ID(false, 2000, "잘못된 user Id입니다."),

    // tumble(2100~2199)
    INVALID_TUMBLE_ID(false, 2001, "잘못된 tumble Id 입니다."),

    /**
     * 3000: Response 오류
     */
    // user(3000~3099)

    // tumble(3100~3199)


    /**
     * 4000: DB, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패했습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}

