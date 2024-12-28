import edu.zhekadoe.tennisscoreboard.model.score.SetScore;
import edu.zhekadoe.tennisscoreboard.model.score.State;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetTests {
    @Test
    public void TwentyFourPointsWinSet() {
        SetScore score = new SetScore();

        // first player wins 23 points
        for (int i = 0; i < 23; i++) {
            assertEquals(State.ONGOING, score.pointWon(0));
        }

        // first player wins the set by winning the 24th point
        assertEquals(State.PLAYER_ONE_WON, score.pointWon(0));
    }

}
