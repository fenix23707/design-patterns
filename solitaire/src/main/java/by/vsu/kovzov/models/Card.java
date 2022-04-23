package by.vsu.kovzov.models;

public class Card {
    public final CardRank rank;

    public final CardSuit suit;

    public final CardColor color;

    public Card(CardRank rank, CardSuit suit, CardColor color) {
        this.rank = rank;
        this.suit = suit;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", suit=" + suit +
                ", color=" + color +
                '}';
    }

    public enum CardRank {
        ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO
    }

    public enum CardSuit {
        SPADES, HEARTS, CLUBS, DIAMONDS
    }

    public enum CardColor {
        RED, BLACK
    }
}
