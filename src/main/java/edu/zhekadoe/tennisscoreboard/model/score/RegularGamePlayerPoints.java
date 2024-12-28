package edu.zhekadoe.tennisscoreboard.model.score;

public enum RegularGamePlayerPoints {
    ZERO("00"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("AD");

    RegularGamePlayerPoints(String title) {
        this.title = title;
    }

    public RegularGamePlayerPoints next() {
        if (this == ADVANTAGE) {
            throw new IllegalArgumentException("Cannot call next() on ADVANTAGE");
        } else {
            return RegularGamePlayerPoints.values()[this.ordinal() + 1];
        }
    }
    private final String title;

    public String getTitle() {
        return title;
    }
}
