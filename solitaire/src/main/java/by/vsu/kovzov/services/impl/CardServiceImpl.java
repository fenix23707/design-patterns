package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.models.Card;
import by.vsu.kovzov.services.CardService;
import javafx.scene.image.Image;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CardServiceImpl implements CardService {
    private static final String IMAGE_DIR = "assets/";

    private final ClassLoader LOADER = getClass().getClassLoader();

    @Override
    public Image getImage(Card card) {
        String filePath = IMAGE_DIR + card.getSuit() + "_" + card.getRank() + ".png";
        try(InputStream is = LOADER.getResource(filePath).openStream()) {
            return new Image(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
