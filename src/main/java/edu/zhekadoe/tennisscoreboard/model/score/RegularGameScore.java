package edu.zhekadoe.tennisscoreboard.model.score;

public class RegularGameScore extends GameScore<RegularGamePlayerPoints> {
    @Override
    protected RegularGamePlayerPoints getZeroScore() {
        return RegularGamePlayerPoints.ZERO;
    }

    public State pointWon(int playerNumber) {
        RegularGamePlayerPoints playerScore = getPlayerScore(playerNumber);

        if (playerScore.ordinal() <= RegularGamePlayerPoints.THIRTY.ordinal()) {
            // 0:X, 15:X or 30:X
            setPlayerScore(playerNumber, playerScore.next());
        } else if (playerScore == RegularGamePlayerPoints.FORTY) {
            // 40:X
            RegularGamePlayerPoints oppositePlayerScore = getOppositePlayerScore(playerNumber);

            if (oppositePlayerScore == RegularGamePlayerPoints.ADVANTAGE) {
                // 40:AD
                setOppositePlayerScore(playerNumber, RegularGamePlayerPoints.FORTY);
            } else if (oppositePlayerScore == RegularGamePlayerPoints.FORTY) {
                // 40:40
                setPlayerScore(playerNumber, RegularGamePlayerPoints.ADVANTAGE);
            } else {
                // 40:0, 40:15 or 40:30
                return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
            }
        } else if (playerScore == RegularGamePlayerPoints.ADVANTAGE) {
            // AD:X -> wins the game
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        } else {
            throw new IllegalArgumentException("Cannot call pointWon() on ADVANTAGE");
        }

        return State.ONGOING;
    }
}
