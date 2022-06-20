package by.vsu.kovzov;

import java.awt.*;
import java.util.Collection;

public class Picture extends Canvas {
	private Collection<Object> figures;

	public Picture(Collection<Object> figures) {
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
		for(Object figure : figures) {
			if(figure instanceof Point) {
				((Point)figure).paint(g2);
			} else if(figure instanceof Line) {
				((Line)figure).paint(g2);
			} else if(figure instanceof Rectangle) {
				((Rectangle)figure).paint(g2);
			} else if(figure instanceof RectangleBorder) {
				((RectangleBorder)figure).paint(g2);
			} else if(figure instanceof Circle) {
				((Circle)figure).paint(g2);
			} else if(figure instanceof CircleBorder) {
				((CircleBorder)figure).paint(g2);
			} else if(figure instanceof Polyline) {
				((Polyline)figure).paint(g2);
			} else if(figure instanceof Polygon) {
				((Polygon)figure).paint(g2);
			} else if(figure instanceof Colour) {
				((Colour)figure).paint(g2);
			} else if(figure instanceof LinearGradient) {
				((LinearGradient)figure).paint(g2);
			} else if(figure instanceof RadialGradient) {
				((RadialGradient)figure).paint(g2);
			} else if(figure instanceof Stroke) {
				((Stroke)figure).paint(g2);
			}
		}
	}
}
