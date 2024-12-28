package edu.zhekadoe.tennisscoreboard.model;

import edu.zhekadoe.tennisscoreboard.model.score.Score;
import edu.zhekadoe.tennisscoreboard.model.score.SetScore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Match {
    @Id
    @GeneratedValue
    private Long id;

    @Transient
    private final Score<?> score = new SetScore();

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Player playerOne;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Player playerTwo;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Player winner;

    public Match(String playerOne, String playerTwo) {
        this.playerOne = new Player(playerOne);
        this.playerTwo = new Player(playerTwo);
    }

    public Match(Player playerOne, Player playerTwo, boolean firstPlayerWon) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.winner = firstPlayerWon ? playerOne : playerTwo;
    }
}
