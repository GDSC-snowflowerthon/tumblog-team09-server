package snowflake.tumblog.tumble.domain;

public enum Size {
    SMALL(39.0),
    MEDIUM(52.1),
    LARGE(64.9);

    private final double carbon;

    Size(double carbon) {
        this.carbon = carbon;
    }

    public double getCarbon() {
        return carbon;
    }
}
