package by.vsu.kovzov.games;

import by.vsu.kovzov.desk.StandardCardDesk;
import by.vsu.kovzov.models.Card;
import by.vsu.kovzov.services.GameLayoutService;
import javafx.util.Pair;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class MonteCarlo extends CardGame {
    public static final int LAYOUT_SIZE = 28;

    public static final int COLUMNS_COUNT = 7;

    private StandardCardDesk cardDesk;

    private Card save;

    private GameLayoutService gameLayoutService;

    public MonteCarlo(GameLayoutService gameLayoutService) {
        this.gameLayoutService = gameLayoutService;
        init();
    }

    public void init() {
        this.cardDesk = new StandardCardDesk();
    }

    public StandardCardDesk getCardDesk() {
        return cardDesk;
    }

    @Override
    public void choose(int row, int col) {
        Card card = gameLayoutService.getCard(row, col);
        if (this.save == null) {
            this.save = card;
            gameLayoutService.highlight(row, col);
        } else {
            move(save, card);
            this.save = null;
        }
    }

    private void move(Card c1, Card c2) {
        if (isSimilarRank(c1, c2)) {
            remove(c1, c2);
        } else {
            gameLayoutService.cancelHighlight(c1);
        }

    }

    private void remove(Card c1, Card c2) {
        gameLayoutService.remove(c1);
        gameLayoutService.remove(c2);
    }

    private boolean isSimilarRank(Card c1, Card c2) {
        return c1.getRank().equals(c2.getRank());
    }
}
