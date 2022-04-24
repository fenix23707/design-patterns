package by.vsu.kovzov.games;

import by.vsu.kovzov.desk.CardDesk;
import by.vsu.kovzov.models.Card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MonteCarloLayout {
    public static final int LAYOUT_SIZE = 28;

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


}
