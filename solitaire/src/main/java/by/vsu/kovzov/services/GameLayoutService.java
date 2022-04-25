package by.vsu.kovzov.services;

import by.vsu.kovzov.games.MonteCarlo;
import by.vsu.kovzov.games.MonteCarloLayout;
import by.vsu.kovzov.models.Card;
import javafx.scene.layout.GridPane;

import java.util.List;

public interface GameLayoutService {

    void update(GridPane pane, MonteCarlo monteCarlo);

    void show(GridPane pane, MonteCarlo monteCarlo);

    void clear(GridPane pane);
}
