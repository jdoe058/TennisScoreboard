package edu.zhekadoe.tennisscoreboard.model.score;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {
    private final List<T> playerScores = new ArrayList<>();

    protected abstract T getZeroScore();

    public Score() {
        playerScores.add(getZeroScore());
        playerScores.add(getZeroScore());
    }

    public T getPlayerScore(int playerNumber) {
        return playerScores.get(playerNumber);
    }

    public T getOppositePlayerScore(int playerNumber) {
        return playerScores.get(playerNumber == 0 ? 1 : 0);
    }

    public void setPlayerScore(int playerNumber, T playerScore) {
        playerScores.set(playerNumber, playerScore);
    }

    public void setOppositePlayerScore(int playerNumber, T playerScore) {
        playerScores.set(playerNumber == 0 ? 1 : 0, playerScore);
    }

    abstract State pointWon(int playerNumber);
}
