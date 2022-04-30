package by.vsu.kovzov.desk;

import by.vsu.kovzov.models.Card;

import java.util.*;

public abstract class CardDesk {

    public abstract Card getCard();

    public abstract List<Card> getCards(int size);

}