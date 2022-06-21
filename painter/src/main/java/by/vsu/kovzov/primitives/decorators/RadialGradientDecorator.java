package by.vsu.kovzov.primitives.decorators;

import by.vsu.kovzov.primitives.Circle;
import by.vsu.kovzov.primitives.Paintable;

import java.awt.*;

public class RadialGradientDecorator implements Paintable {
	private MultipleGradientPaint gradient;

	private Paintable paintable;

	public RadialGradientDecorator(Circle circle, Color centerColor, Color borderColor) {
		this.paintable = circle;
		gradient = new RadialGradientPaint(circle.getCenter().getX(), circle.getCenter().getY(), circle.getRadius(), new float[] {0, 1}, new Color[] {centerColor, borderColor});
	}

	public void paint(Graphics2D g) {
		Paint old = g.getPaint();
		g.setPaint(gradient);

		this.paintable.paint(g);

		g.setPaint(old);
	}
}
