package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.desk.CardDesk;
import by.vsu.kovzov.games.MonteCarlo;
import by.vsu.kovzov.models.Card;
import by.vsu.kovzov.services.CardService;
import by.vsu.kovzov.services.GameLayoutService;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.vsu.kovzov.Constants.CARD_DEFAULT_OPACITY;
import static by.vsu.kovzov.Constants.CARD_HIGHLIGHT_OPACITY;
import static by.vsu.kovzov.games.MonteCarlo.COLS_COUNT;
import static by.vsu.kovzov.games.MonteCarlo.ROWS_COUNT;
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
    public void compress(MonteCarlo monteCarlo) {
        CardDesk cardDesk = monteCarlo.getCardDesk();

        List<Card> cards = gridPane.getChildren().stream()
                .map(node -> (Card) node.getUserData())
                .collect(Collectors.toList());

        List<Card> additional = cardDesk.getCards(MonteCarlo.LAYOUT_SIZE - cards.size());
        cards.addAll(additional);

        clear();
        show(cards);
    }

    @Override
    public List<Pair<Card, Card>> getCardPair(MonteCarlo monteCarlo) {
        List<Pair<Card, Card>> cardPair = new ArrayList<>();

        for (int row = 0; row < ROWS_COUNT; row++) {
            for (int col = 0; col < COLS_COUNT; col++) {
                Card card = getCard(row, col);
                Card leftCard = getCard(row, col + 1);
                Card bottomCard = getCard(row + 1, col);
                Card diagonalCard = getCard(row + 1, col + 1);
                Card reverseDiagonalCard = getCard(row + 1, col - 1);
                for (Card c : new Card[]{leftCard, bottomCard, diagonalCard, reverseDiagonalCard}) {
                    if (monteCarlo.isCardPair(c, card)) {
                        cardPair.add(new Pair<>(c, card));
                    }
                }

            }
        }
        return cardPair;
    }

    @Override
    public void highlight(int row, int col) {
        getNode(row, col)
                .orElseThrow(() -> new RuntimeException(format("node on row=%s col=%s didn't find", row, col)))
                .setOpacity(CARD_HIGHLIGHT_OPACITY);
    }

    @Override
    public void highlight(Card card) {
        getNode(card)
                .orElseThrow(() -> new RuntimeException(format("node with card=%s didn't find", card)))
                .setOpacity(CARD_HIGHLIGHT_OPACITY);
    }

    @Override
    public void cancelHighlight(int row, int col) {
        getNode(row, col)
                .orElseThrow(() -> new RuntimeException(format("node on row=%s col=%s didn't find", row, col)))
                .setOpacity(CARD_DEFAULT_OPACITY);
    }

    @Override
    public void cancelHighlight(Card card) {
        getNode(card)
                .orElseThrow(() -> new RuntimeException(format("node with card=%s didn't find", card)))
                .setOpacity(CARD_DEFAULT_OPACITY);
    }

    @Override
    public Card getCard(int row, int col) {
        Node node = getNode(row, col).orElse(null);
        return node != null ? (Card) node.getUserData() : null;
    }

    @Override
    public Pair<Integer, Integer> getRowCol(Card card) {
        Node node = getNode(card)
                .orElseThrow(() -> new RuntimeException(format("node with card=%s didn't find", card)));
        return new Pair<>(gridPane.getRowIndex(node), gridPane.getColumnIndex(node));
    }

    @Override
    public void remove(int row, int col) {
        gridPane.getChildren().remove(getNode(row, col).get());
    }

    @Override
    public void remove(Card card) {
        gridPane.getChildren().remove(getNode(card).get());
    }

    @Override
    public void update(MonteCarlo monteCarlo) {
        clear();
        CardDesk cardDesk = monteCarlo.getCardDesk();
        List<Card> cards = cardDesk.getCards(MonteCarlo.LAYOUT_SIZE);
        show(cards);
    }

    @Override
    public void show(List<Card> cards) {
        int col;
        int row = -1;
        for (int i = 0; i < cards.size(); i++) {
            col = i % gridPane.getColumnCount();
            if (col == 0) {
                row++;
            }
            gridPane.add(toImageView(cards.get(i)), col, row);
        }
    }

    @Override
    public void clear() {
        gridPane.getChildren().clear();
    }

    @Override
    public int size() {
        return gridPane.getChildren().size();
    }

    private List<Card> getCards() {
        return gridPane.getChildren().stream()
                .map(node -> (Card) node.getUserData())
                .collect(Collectors.toList());
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

    private Optional<Node> getNode(Card card) {
        return gridPane.getChildren().stream()
                .filter(node -> node.getUserData().equals(card))
                .findAny();
//                .orElseThrow(() -> new RuntimeException("can't find node for this card: " + card));
    }

    private Optional<Node> getNode(int row, int col) {
        return gridPane.getChildren().stream()
                .filter(node -> gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == col)
                .findAny();
//                .orElseThrow(() -> new RuntimeException(format("can't find node on this position: row=%s col=%s", row, col)));
    }
}
