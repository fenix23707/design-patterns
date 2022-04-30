package by.vsu.kovzov.desk;

import by.vsu.kovzov.models.Card;

import java.util.*;

public class StandardCardDesk extends CardDesk {
    public static final int CARD_SIZE = 52;

    private Queue<Card> cards;

    @Override
    public List<Card> getCards(int size) {
        List<Card> result = new ArrayList<>(size);
        for (int i = 0; i < size && !cards.isEmpty(); i++) {
            result.add(getCard());
        }
        return result;
    }

    @Override
    public Card getCard() {
        return cards.poll();
    }

    public StandardCardDesk() {
        this.cards = new LinkedList<>(generateCardDesk());
    }

    protected List<Card> generateCardDesk() {
        List<Card> cards = new ArrayList<>(CARD_SIZE);
        for (Card.CardRank rank : Card.CardRank.values()) {
            for (Card.CardSuit suit : Card.CardSuit.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }

    @Override
    public int size() {
        return cards.size();
    }

}
