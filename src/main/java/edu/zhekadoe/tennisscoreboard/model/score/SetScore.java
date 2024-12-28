package edu.zhekadoe.tennisscoreboard.model.score;

public class SetScore extends Score<Integer> {

    private GameScore<?> currentGame;

    public SetScore() {
        this.currentGame = new RegularGameScore();
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public State pointWon(int playerNumber) {
        State state = currentGame.pointWon(playerNumber);
        if (state == State.PLAYER_ONE_WON) {
            return gameWon(0);
        } else if (state == State.PLAYER_TWO_WON) {
            return gameWon(1);
        }

        return State.ONGOING;
    }

    private State gameWon(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);
        this.currentGame = new RegularGameScore();
        if (getPlayerScore(playerNumber) == 6) {
            // todo 2 games advantage & tiebreak logic

            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }
        return State.ONGOING;
    }
}
