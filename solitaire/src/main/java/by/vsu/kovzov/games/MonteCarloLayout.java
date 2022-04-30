package by.vsu.kovzov.games;

import by.vsu.kovzov.desk.CardDesk;
import by.vsu.kovzov.models.Card;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MonteCarloLayout {
    public static final int LAYOUT_SIZE = 28;

    public static final int COLUMNS_COUNT = 7;

    private ArrayList<Card> cards;

    public MonteCarloLayout() {
        this.cards = new ArrayList<>(LAYOUT_SIZE);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void fill(CardDesk cardDesk) {
        while (cards.size() < LAYOUT_SIZE) {
            cards.add(cardDesk.getCard());
        }
    }

    public void remove(Card card) {
        cards.remove(card);
    }

    public Card getCard(int row, int col) {
        return cards.get(row * COLUMNS_COUNT + col);
    }

    public Optional<Pair<Integer, Integer>> findCard(Card card) {
        int index = cards.indexOf(card);
        if (index < 0) {
            return Optional.empty();
        }
        Pair<Integer, Integer> pair = new Pair<>(index / COLUMNS_COUNT, index % COLUMNS_COUNT);
        return Optional.of(pair);
    }
}
