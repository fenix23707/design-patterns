package by.vsu.kovzov;

import java.awt.*;

public class LinearGradient {
	private MultipleGradientPaint gradient;

	public LinearGradient(Line line, Color beginColor, Color endColor) {
		gradient = new LinearGradientPaint(line.getBegin().getX(), line.getBegin().getY(), line.getEnd().getX(), line.getEnd().getY(), new float[] {0, 1}, new Color[] {beginColor, endColor});
	}

	public void paint(Graphics2D g) {
		g.setPaint(gradient);
	}
}
