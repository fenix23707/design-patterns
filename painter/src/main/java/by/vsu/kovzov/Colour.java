package by.vsu.kovzov;

import java.awt.*;

public class Colour {
	private Color color;

	public Colour(Color color) {
		this.color = color;
	}

	public void paint(Graphics2D g) {
		g.setColor(color);
	}
}
