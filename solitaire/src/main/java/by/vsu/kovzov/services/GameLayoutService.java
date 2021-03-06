package by.vsu.kovzov.services;

import by.vsu.kovzov.games.MonteCarlo;
import by.vsu.kovzov.models.Card;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.List;

public interface GameLayoutService {

    void setGridPane(GridPane pane);

    void highlight(int row, int col);

    void highlight(Card card);

    void cancelHighlight(int row, int col);

    void cancelHighlight(Card card);

    Card getCard(int row, int col);

    Pair<Integer, Integer> getRowCol(Card card);

    void remove(int row, int col);

    void remove(Card card);

    void update( MonteCarlo monteCarlo);

    void show(List<Card> cards);

    void clear();

    void compress(MonteCarlo monteCarlo);

    int size();

    List<Pair<Card, Card>> getCardPair(MonteCarlo monteCarlo);
}
