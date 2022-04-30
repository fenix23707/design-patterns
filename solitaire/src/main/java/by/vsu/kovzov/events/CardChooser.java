package by.vsu.kovzov.events;

import by.vsu.kovzov.games.Choosable;
import by.vsu.kovzov.services.GameLayoutService;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class CardChooser implements EventHandler<MouseEvent> {

    private Choosable choosable;

    public void setChoosable(Choosable choosable) {
        this.choosable = choosable;
    }

    @Override
    public void handle(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        int row = GridPane.getRowIndex(node);
        int col = GridPane.getColumnIndex(node);
        choosable.choose(row, col);
    }
}
