package by.vsu.kovzov.controlles;

import by.vsu.kovzov.events.CardChooser;
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
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
@FxmlView("/fxml/app.fxml")
public class AppController implements Initializable {

    @FXML
    private GridPane gameLayout;

    private MonteCarlo monteCarlo;

    @Autowired
    private GameLayoutService gameLayoutService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameLayoutService.setGridPane(gameLayout);
    }

    public void startGame(MouseEvent mouseEvent) {
        this.monteCarlo = new MonteCarlo(gameLayoutService);
        CardChooser chooser = new CardChooser();
        chooser.setChoosable(monteCarlo);
        gameLayout.addEventHandler(MouseEvent.MOUSE_CLICKED, chooser);

        gameLayoutService.update(monteCarlo);

    }
}
