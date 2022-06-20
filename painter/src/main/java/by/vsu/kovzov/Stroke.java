package by.vsu.kovzov;

import java.awt.*;

public class Stroke {
	private java.awt.Stroke stroke;

	public Stroke(int width) {
		stroke = new BasicStroke((float)width);
	}

	public void paint(Graphics2D g) {
		g.setStroke(stroke);
	}
}
