package edu.zhekadoe.tennisscoreboard.model.score;

public enum RegularGamePlayerPoints {
    ZERO, FIFTEEN, THIRTY, FORTY, ADVANTAGE;

    public RegularGamePlayerPoints next() {
        if (this == ADVANTAGE) {
            throw new IllegalArgumentException("Cannot call next() on ADVANTAGE");
        } else {
            return RegularGamePlayerPoints.values()[this.ordinal() + 1];
        }
    }
}
