package snowflake.tumblog.tumble.domain;

import java.util.Arrays;

public enum Size {
    S(39.0),
    M(52.1),
    L(64.9);

    private final double carbon;

    Size(double carbon) {
        this.carbon = carbon;
    }

    public double getCarbon() {
        return carbon;
    }

    public static Size from(String size) {
        return Arrays.stream(Size.values())
            .filter(s -> s.name().equals(size))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 사이즈가 없습니다."));
    }
}
