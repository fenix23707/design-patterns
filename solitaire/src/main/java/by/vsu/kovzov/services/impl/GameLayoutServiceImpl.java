package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.games.MonteCarlo;
import by.vsu.kovzov.models.Card;
import by.vsu.kovzov.services.CardService;
import by.vsu.kovzov.services.GameLayoutService;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameLayoutServiceImpl implements GameLayoutService {
    private CardService cardService;

    @Autowired
    public GameLayoutServiceImpl(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void update(GridPane pane, MonteCarlo monteCarlo) {
        clear(pane);
        show(pane, monteCarlo);
    }

    @Override
    public void show(GridPane pane, MonteCarlo monteCarlo) {
        List<Card> cards = monteCarlo.getLayout().getCards();
        Bounds bounds = pane.getCellBounds(0, 0);

        int col;
        int row = -1;
        for (int i = 0; i < cards.size(); i++) {
            col = i % pane.getColumnCount();
            if (col == 0) {
                row++;
            }

            Image image = cardService.getImage(cards.get(i));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(bounds.getWidth() - 15);
            imageView.setFitHeight(bounds.getHeight() - 15);
            imageView.setOpacity(0.8);
            pane.add(imageView, col, row);
        }
    }

    @Override
    public void clear(GridPane pane) {
        pane.getChildren().clear();
    }
}
