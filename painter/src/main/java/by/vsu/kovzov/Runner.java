package by.vsu.kovzov;

import by.vsu.kovzov.primitives.*;
import by.vsu.kovzov.primitives.Point;
import by.vsu.kovzov.primitives.Polygon;
import by.vsu.kovzov.primitives.Rectangle;
import by.vsu.kovzov.primitives.decorators.ColorDecorator;
import by.vsu.kovzov.primitives.decorators.LinearGradientDecorator;
import by.vsu.kovzov.primitives.decorators.RadialGradientDecorator;
import by.vsu.kovzov.primitives.decorators.StrokeDecorator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Runner {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        Collection<Paintable> figures = new ArrayList<>();

        //sea
        figures.add(new LinearGradientDecorator(new Rectangle(new Point(275, 300), 550, 100), new Line(new Point(0, 250), new Point(0, 350)), new Color(0, 128, 255), new Color(0, 0, 128)));

        // sun
        figures.add(new ColorDecorator(new Circle(new by.vsu.kovzov.primitives.Point(450, 50), 25), Color.YELLOW));

        // паруса
        figures.add(new ColorDecorator(new StrokeDecorator(new Line(new Point(200, 25), new Point(200, 125)), 2), Color.ORANGE));
        figures.add(new ColorDecorator(new StrokeDecorator(new Line(new Point(175, 50), new Point(225, 50)), 2), Color.ORANGE));
        figures.add(new ColorDecorator(new StrokeDecorator(new Polyline(new Point(50, 200), new Point(200, 50), new Point(500, 200)), 2), Color.BLACK));

        // кабина на палубе
        figures.add(new ColorDecorator(new Rectangle(new Point(225, 175), 150, 50), new Color(255, 200, 200)));
        figures.add(new ColorDecorator(new StrokeDecorator(new RectangleBorder(new Point(225, 175), 150, 50), 1), Color.RED));

        figures.add(new ColorDecorator(new Rectangle(new Point(212, 137), 75, 25), new Color(255, 200, 200)));
        figures.add(new ColorDecorator(new StrokeDecorator(new RectangleBorder(new Point(212, 137), 75, 25), 1), Color.RED));

        // маленькие окна
        figures.add(new ColorDecorator(new Point(175, 175), new Color(0, 128, 0)));
        figures.add(new ColorDecorator(new Point(200, 175), new Color(0, 128, 0)));
        figures.add(new ColorDecorator(new Point(225, 175), new Color(0, 128, 0)));
        figures.add(new ColorDecorator(new Point(250, 175), new Color(0, 128, 0)));
        figures.add(new ColorDecorator(new Point(275, 175), new Color(0, 128, 0)));

        // палуба
        figures.add(new ColorDecorator(new Polygon(new Point(50, 200), new Point(500, 200), new Point(400, 300), new Point(150, 300)), Color.LIGHT_GRAY));
        figures.add(new ColorDecorator(new StrokeDecorator(
                new Polyline(new Point(50, 200), new Point(500, 200), new Point(400, 300), new Point(150, 300), new Point(50, 200)), 2), Color.GRAY));

        // windows
        figures.add(new RadialGradientDecorator(new Circle(new Point(175, 250), 25), Color.CYAN, Color.WHITE));
        figures.add(new ColorDecorator(new StrokeDecorator(new CircleBorder(new Point(175, 250), 25), 3), Color.MAGENTA));

        figures.add(new RadialGradientDecorator(new Circle(new Point(275, 250), 25), Color.CYAN, Color.WHITE));
        figures.add(new ColorDecorator(new StrokeDecorator(new CircleBorder(new Point(275, 250), 25), 3), Color.MAGENTA));

        figures.add(new RadialGradientDecorator(new Circle(new Point(375, 250), 25), Color.CYAN, Color.WHITE));
        figures.add(new ColorDecorator(new StrokeDecorator(new CircleBorder(new Point(375, 250), 25), 3), Color.MAGENTA));

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new MainFrame("Ship", 550, 350, new Picture(figures));
    }
}
