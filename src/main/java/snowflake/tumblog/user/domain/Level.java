package snowflake.tumblog.user.domain;

import java.util.Arrays;

public enum Level {
    LV_1(1, 0, 100),
    LV_2(2, 100, 400),
    LV_3(3, 400, 900),
    LV_4(4, 900, 1600),
    LV_5(5, 1600, 5000);

    private final int description;
    private final int inclusiveRange;
    private final int exclusiveRange;

    Level(int description, int inclusiveRange, int exclusiveRange) {
        this.description = description;
        this.inclusiveRange = inclusiveRange;
        this.exclusiveRange = exclusiveRange;
    }

    public int getDescription() {
        return description;
    }

    public static Level from(Integer experience) {
        return Arrays.stream(Level.values())
            .filter(
	level -> level.inclusiveRange <= experience && level.exclusiveRange > experience)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 레벨이 없습니다."));
    }
}
