import edu.zhekadoe.tennisscoreboard.model.score.RegularGameScore;
import edu.zhekadoe.tennisscoreboard.model.score.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularGameTests {
    @Test
    public void FourPointsWinGame() {
        RegularGameScore score = new RegularGameScore();

        // first player wins 3 points
        for (int i = 0; i < 3; i++) {
            assertEquals(State.ONGOING, score.pointWon(0));
        }

        // first player wins the game by winning the 4th point
        assertEquals(State.PLAYER_ONE_WON, score.pointWon(0));
    }

    @Test
    public void testAdvantage() {
        RegularGameScore score = new RegularGameScore();

        // both players win 3 point
        for (int i = 0; i < 3; i++) {
            score.pointWon(0);
            score.pointWon(1);
        }

        // current score is 40:40
        // 4h point doesn't with the game (score become AD:40)
        assertEquals(State.ONGOING, score.pointWon(0));

        // current score is AD:40
        // return to 40:40
        assertEquals(State.ONGOING, score.pointWon(1));

        // First player wins 2 points in a row to win the game
        assertEquals(State.ONGOING, score.pointWon(0));
        assertEquals(State.PLAYER_ONE_WON, score.pointWon(0));
    }
}
