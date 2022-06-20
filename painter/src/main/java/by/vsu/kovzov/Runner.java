package by.vsu.kovzov;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Runner {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		Collection<Object> figures = new ArrayList<>();
		figures.add(new LinearGradient(new Line(new Point(0, 250), new Point(0, 350)), new Color(0, 128, 255), new Color(0, 0, 128)));
		figures.add(new Rectangle(new Point(275, 300), 550, 100));
		figures.add(new Colour(Color.YELLOW));
		figures.add(new Circle(new Point(450, 50), 25));
		figures.add(new Colour(Color.ORANGE));
		figures.add(new Stroke(2));
		figures.add(new Line(new Point(200, 25), new Point(200, 125)));
		figures.add(new Colour(Color.ORANGE));
		figures.add(new Stroke(2));
		figures.add(new Line(new Point(175, 50), new Point(225, 50)));
		figures.add(new Colour(Color.BLACK));
		figures.add(new Stroke(2));
		figures.add(new Polyline(new Point(50, 200), new Point(200, 50), new Point(500, 200)));
		figures.add(new Colour(new Color(255, 200, 200)));
		figures.add(new Rectangle(new Point(225, 175), 150, 50));
		figures.add(new Colour(Color.RED));
		figures.add(new Stroke(1));
		figures.add(new RectangleBorder(new Point(225, 175), 150, 50));
		figures.add(new Colour(new Color(255, 200, 200)));
		figures.add(new Rectangle(new Point(212, 137), 75, 25));
		figures.add(new Colour(Color.RED));
		figures.add(new Stroke(1));
		figures.add(new RectangleBorder(new Point(212, 137), 75, 25));
		figures.add(new Colour(new Color(0, 128, 0)));
		figures.add(new Point(175, 175));
		figures.add(new Colour(new Color(0, 128, 0)));
		figures.add(new Point(200, 175));
		figures.add(new Colour(new Color(0, 128, 0)));
		figures.add(new Point(225, 175));
		figures.add(new Colour(new Color(0, 128, 0)));
		figures.add(new Point(250, 175));
		figures.add(new Colour(new Color(0, 128, 0)));
		figures.add(new Point(275, 175));
		figures.add(new Colour(Color.LIGHT_GRAY));
		figures.add(new Polygon(new Point(50, 200), new Point(500, 200), new Point(400, 300), new Point(150, 300)));
		figures.add(new Colour(Color.GRAY));
		figures.add(new Stroke(2));
		figures.add(new Polyline(new Point(50, 200), new Point(500, 200), new Point(400, 300), new Point(150, 300), new Point(50, 200)));
		figures.add(new RadialGradient(new Circle(new Point(175, 250), 25), Color.CYAN, Color.WHITE));
		figures.add(new Circle(new Point(175, 250), 25));
		figures.add(new Colour(Color.MAGENTA));
		figures.add(new Stroke(3));
		figures.add(new CircleBorder(new Point(175, 250), 25));
		figures.add(new RadialGradient(new Circle(new Point(275, 250), 25), Color.CYAN, Color.WHITE));
		figures.add(new Circle(new Point(275, 250), 25));
		figures.add(new Colour(Color.MAGENTA));
		figures.add(new Stroke(3));
		figures.add(new CircleBorder(new Point(275, 250), 25));
		figures.add(new RadialGradient(new Circle(new Point(375, 250), 25), Color.CYAN, Color.WHITE));
		figures.add(new Circle(new Point(375, 250), 25));
		figures.add(new Colour(Color.MAGENTA));
		figures.add(new Stroke(3));
		figures.add(new CircleBorder(new Point(375, 250), 25));
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new MainFrame("Yacht", 550, 350, new Picture(figures));
	}
}
