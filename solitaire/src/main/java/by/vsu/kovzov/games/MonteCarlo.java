package by.vsu.kovzov.games;

import by.vsu.kovzov.desk.StandardCardDesk;
import by.vsu.kovzov.models.Card;
import by.vsu.kovzov.services.GameLayoutService;
import javafx.util.Pair;

import static java.lang.Math.abs;

public class MonteCarlo extends CardGame {
    public static final int LAYOUT_SIZE = 28;

    public static final int COLS_COUNT = 7;

    public static final int ROWS_COUNT = 4;

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
    public synchronized void choose(int row, int col) {
        Card card = gameLayoutService.getCard(row, col);
        if (this.save == null) {
            this.save = card;
            gameLayoutService.highlight(row, col);
        } else {
            move(save, card);
            this.save = null;
        }
    }

    public void compress() {
        gameLayoutService.compress(this);
    }

    private void move(Card c1, Card c2) {
        if (isCardPair(c1, c2)) {
            remove(c1, c2);
        } else {
            gameLayoutService.cancelHighlight(c1);
        }
        System.out.println(gameLayoutService.getCardPair(this).size());
    }

    private void remove(Card c1, Card c2) {
        gameLayoutService.remove(c1);
        gameLayoutService.remove(c2);
    }

    public boolean isCardPair(Card c1, Card c2) {
        return c1 != null && c2!= null && c1 != c2 && isSimilarRank(c1, c2) && isClose(c1, c2);
    }

    private boolean isSimilarRank(Card c1, Card c2) {
        return c1.getRank().equals(c2.getRank());
    }

    private boolean isClose(Card c1, Card c2) {
        Pair<Integer, Integer> p1 = gameLayoutService.getRowCol(c1);
        Pair<Integer, Integer> p2 = gameLayoutService.getRowCol(c2);

        return abs(p1.getKey() - p2.getKey()) <= 1 && abs(p1.getValue() - p2.getValue()) <= 1;
    }

    private boolean isWin() {
        return gameLayoutService.getSize() == 0 && cardDesk.size() == 0;
    }
}
