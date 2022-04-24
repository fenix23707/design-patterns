package by.vsu.kovzov.controlles;

import by.vsu.kovzov.games.MonteCarlo;
import by.vsu.kovzov.games.MonteCarloLayout;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("/fxml/app.fxml")
public class AppController {

    @FXML
    private GridPane gameLayout;

    private MonteCarlo monteCarlo;

    public void startGame(MouseEvent mouseEvent) {
        this.monteCarlo = new MonteCarlo();

    }


    private void fillGameLayout() {
//        gameLayout.add();
    }
}
