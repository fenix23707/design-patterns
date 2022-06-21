package by.vsu.kovzov.primitives.decorators;

import by.vsu.kovzov.primitives.Paintable;

import java.awt.*;

public class ColorDecorator implements Paintable {
    private Paintable paintable;

    private Color color;

    public ColorDecorator(Paintable paintable, Color color) {
        this.paintable = paintable;
        this.color = color;
    }

    @Override
    public void paint(Graphics2D g) {
        Color old = g.getColor();
        g.setColor(this.color);

        this.paintable.paint(g);

        g.setColor(old);
    }
}
