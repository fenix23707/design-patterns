package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.Constants;
import by.vsu.kovzov.desk.CardDesk;
import by.vsu.kovzov.games.MonteCarlo;
import by.vsu.kovzov.games.MonteCarloLayout;
import by.vsu.kovzov.models.Card;
import by.vsu.kovzov.services.CardService;
import by.vsu.kovzov.services.GameLayoutService;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.vsu.kovzov.Constants.CARD_DEFAULT_OPACITY;
import static by.vsu.kovzov.Constants.CARD_HIGHLIGHT_OPACITY;
import static java.lang.String.format;

@Service
public class GameLayoutServiceImpl implements GameLayoutService {

    private CardService cardService;

    private GridPane gridPane;

    @Override
    public void setGridPane(GridPane pane) {
        this.gridPane = pane;
    }

    @Autowired
    public GameLayoutServiceImpl(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void highlight(int row, int col) {
        getNode(row, col).setOpacity(CARD_HIGHLIGHT_OPACITY);
    }

    @Override
    public void highlight(Card card) {
        getNode(card).setOpacity(CARD_HIGHLIGHT_OPACITY);
    }

    @Override
    public void cancelHighlight(int row, int col) {
        getNode(row, col).setOpacity(CARD_DEFAULT_OPACITY);
    }

    @Override
    public void cancelHighlight(Card card) {
        getNode(card).setOpacity(CARD_DEFAULT_OPACITY);
    }

    @Override
    public Card getCard(int row, int col) {
        return (Card) getNode(row, col).getUserData();
    }

    @Override
    public void remove(int row, int col) {
        gridPane.getChildren().remove(getNode(row, col));
    }

    @Override
    public void remove(Card card) {
        gridPane.getChildren().remove(getNode(card));
    }

    @Override
    public void update(MonteCarlo monteCarlo) {
        clear();
        show(monteCarlo);
    }

    @Override
    public void show(MonteCarlo monteCarlo) {
        CardDesk cardDesk = monteCarlo.getCardDesk();
        List<Card> cards = cardDesk.getCards(MonteCarlo.LAYOUT_SIZE);
        int col;
        int row = -1;
        for (int i = 0; i < cards.size(); i++) {
            col = i % gridPane.getColumnCount();
            if (col == 0) {
                row++;
            }
            gridPane.add(toImageView(cardDesk.getCard()), col, row);
        }
    }

    @Override
    public void clear() {
        gridPane.getChildren().clear();
    }

    private ImageView toImageView(Card card) {
        Bounds bounds = gridPane.getCellBounds(0, 0);
        Image image = cardService.getImage(card);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(bounds.getWidth() - 15);
        imageView.setFitHeight(bounds.getHeight() - 15);
        imageView.setOpacity(CARD_DEFAULT_OPACITY);

        imageView.setUserData(card);

        return imageView;
    }

    private Node getNode(Card card) {
        return gridPane.getChildren().stream()
                .filter(node -> node.getUserData().equals(card))
                .findAny()
                .orElseThrow(() -> new RuntimeException("can't find node for this card: " + card));
    }

    private Node getNode(int row, int col) {
        return gridPane.getChildren().stream()
                .filter(node -> gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == col)
                .findAny()
                .orElseThrow(() -> new RuntimeException(format("can't find node on this position: row=%s col=%s", row, col)));
    }
}
