package snowflake.tumblog.user.domain;

import java.util.Arrays;

public enum Level {
    LV_1(0, 100),
    LV_2(100, 400),
    LV_3(400, 900),
    LV_4(900, 1600),
    LV_5(1600, 3000);

    private final int inclusiveRange;
    private final int exclusiveRange;

    Level(int inclusiveRange, int exclusiveRange) {
        this.inclusiveRange = inclusiveRange;
        this.exclusiveRange = exclusiveRange;
    }


    public static Level from(Integer experience) {
        return Arrays.stream(Level.values())
            .filter(
	level -> level.inclusiveRange <= experience && level.exclusiveRange > experience)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 레벨이 없습니다."));
    }
}
