package by.vsu.kovzov;

import by.vsu.kovzov.primitives.*;

import java.awt.*;
import java.awt.Rectangle;
import java.util.Collection;

public class Picture extends Canvas {
	private Collection<Paintable> figures;

	public Picture(Collection<Paintable> figures) {
		this.figures = figures;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		java.awt.Rectangle r = getBounds();
		g2.fillRect(r.x, r.y, r.width, r.height);
		for(Paintable figure : figures) {
			figure.paint(g2);
		}
	}
}
