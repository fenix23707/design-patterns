package by.vsu.kovzov.primitives;

import java.awt.*;

public class Circle implements Paintable {
	private Point center;
	private int radius;
	private int x;
	private int y;
	private int diameter;

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
		x = center.getX() - radius;
		y = center.getY() - radius;
		diameter = 2 * radius;
	}

	public Point getCenter() {
		return center;
	}

	public int getRadius() {
		return radius;
	}

	@Override
	public void paint(Graphics2D g) {
		g.fillOval(x, y, diameter, diameter);
	}
}
