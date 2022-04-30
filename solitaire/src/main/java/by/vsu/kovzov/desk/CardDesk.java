package by.vsu.kovzov.desk;

import by.vsu.kovzov.models.Card;

import java.util.List;

public abstract class CardDesk {

    public abstract Card getCard();

    public abstract List<Card> getCards(int size);

    public abstract int size();

}