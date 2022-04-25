package by.vsu.kovzov.models;

import java.util.Locale;
import java.util.Objects;

public class Card {
    private final CardRank rank;

    private final CardSuit suit;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", suit=" + suit +
                '}';
    }

    public enum CardRank {
        ACE("ace"), KING("king"), QUEEN("queen"), JACK("jack"),
        TEN("10"), NINE("9"), EIGHT("8"), SEVEN("7"), SIX("6"),
        FIVE("5"), FOUR("4"), THREE("3"), TWO("2");

        private final String name;

        CardRank(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum CardSuit {
        SPADES, HEARTS, CLUBS, DIAMONDS;

        @Override
        public String toString() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
