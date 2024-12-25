package edu.zhekadoe.tennisscoreboard.model;

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

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Player playerOne;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Player playerTwo;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Player winner;

    public Match(Player playerOne, Player playerTwo, boolean firstPlayerWon) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.winner = firstPlayerWon ? playerOne : playerTwo;
    }
}
