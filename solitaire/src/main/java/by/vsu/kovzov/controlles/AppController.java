package by.vsu.kovzov.controlles;

import by.vsu.kovzov.games.MonteCarlo;
import by.vsu.kovzov.games.MonteCarloLayout;
import by.vsu.kovzov.models.Card;
import by.vsu.kovzov.services.CardService;
import by.vsu.kovzov.services.GameLayoutService;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@FxmlView("/fxml/app.fxml")
public class AppController {

    @FXML
    private GridPane gameLayout;

    private MonteCarlo monteCarlo;

    @Autowired
    private GameLayoutService gameLayoutService;

    public void startGame(MouseEvent mouseEvent) {
        gameLayout.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node node = event.getPickResult().getIntersectedNode();
                node.setOpacity(1);
                System.out.println("col: " + GridPane.getColumnIndex(node));
                System.out.println("row: " + GridPane.getRowIndex(node));
                gameLayout.getChildren().remove(node);
            }
        });

        this.monteCarlo = new MonteCarlo();
        gameLayoutService.update(gameLayout, monteCarlo);

    }
}
