package by.vsu.kovzov.primitives.decorators;

import by.vsu.kovzov.primitives.Line;
import by.vsu.kovzov.primitives.Paintable;

import java.awt.*;

public class LinearGradientDecorator implements Paintable {
    private MultipleGradientPaint gradient;

    private Paintable paintable;

    public LinearGradientDecorator(Paintable paintable, Line line, Color beginColor, Color endColor) {
        this.paintable = paintable;
        gradient = new LinearGradientPaint(line.getBegin().getX(), line.getBegin().getY(), line.getEnd().getX(), line.getEnd().getY(), new float[] {0, 1}, new Color[] {beginColor, endColor});
    }

    public void paint(Graphics2D g) {
        Paint old = g.getPaint();
        g.setPaint(gradient);

        this.paintable.paint(g);

        g.setPaint(old);
    }
}
