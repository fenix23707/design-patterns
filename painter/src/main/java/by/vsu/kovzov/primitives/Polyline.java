package by.vsu.kovzov.primitives;

import java.awt.*;

public class Polyline implements Paintable {
	private int[] x;
	private int[] y;
	private int n;

	public Polyline(Point ... points) {
		n = points.length;
		x = new int[n];
		y = new int[n];
		for(int i = 0; i < n; i++) {
			x[i] = points[i].getX();
			y[i] = points[i].getY();
		}
	}

	public void paint(Graphics2D g) {
		g.drawPolyline(x, y, n);
	}
}
