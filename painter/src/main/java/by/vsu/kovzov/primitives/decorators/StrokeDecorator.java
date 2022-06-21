package by.vsu.kovzov.primitives.decorators;

import by.vsu.kovzov.primitives.Paintable;

import java.awt.*;

public class StrokeDecorator implements Paintable {

	private Stroke stroke;

	private Paintable paintable;

	public StrokeDecorator(Paintable paintable ,int width) {
		stroke = new BasicStroke((float)width);
		this.paintable = paintable;
	}

	@Override
	public void paint(Graphics2D g) {
		Stroke old = g.getStroke();
		g.setStroke(this.stroke);

		paintable.paint(g);

		g.setStroke(old);
	}
}
