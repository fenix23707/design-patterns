package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.services.GameService;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    @Override
    public void informWin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Win");
        alert.setHeaderText(null);
        alert.setContentText("You are win, congratulations");

        alert.showAndWait();
    }

    @Override
    public void informLose() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lose");
        alert.setHeaderText(null);
        alert.setContentText("You are lose, i'm sorry");

        alert.showAndWait();
    }
}
