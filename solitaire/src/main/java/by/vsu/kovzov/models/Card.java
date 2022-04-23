package by.vsu.kovzov.models;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit && color == card.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit, color);
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
