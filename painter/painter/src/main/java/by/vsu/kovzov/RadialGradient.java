package by.vsu.kovzov;

import java.awt.*;

public class RadialGradient {
	private MultipleGradientPaint gradient;

	public RadialGradient(Circle circle, Color centerColor, Color borderColor) {
		gradient = new RadialGradientPaint(circle.getCenter().getX(), circle.getCenter().getY(), circle.getRadius(), new float[] {0, 1}, new Color[] {centerColor, borderColor});
	}

	public void paint(Graphics2D g) {
		g.setPaint(gradient);
	}
}
