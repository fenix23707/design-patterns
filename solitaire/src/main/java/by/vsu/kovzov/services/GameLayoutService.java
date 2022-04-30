package by.vsu.kovzov.services;

import by.vsu.kovzov.games.MonteCarlo;
import by.vsu.kovzov.games.MonteCarloLayout;
import by.vsu.kovzov.models.Card;
import javafx.scene.layout.GridPane;

import java.util.List;

public interface GameLayoutService {

    void setGridPane(GridPane pane);

    void highlight(int row, int col);

    void highlight(Card card);

    void cancelHighlight(int row, int col);

    void cancelHighlight(Card card);

    Card getCard(int row, int col);

    void remove(int row, int col);

    void remove(Card card);

    void update( MonteCarlo monteCarlo);

    void show( MonteCarlo monteCarlo);

    void clear();
}
