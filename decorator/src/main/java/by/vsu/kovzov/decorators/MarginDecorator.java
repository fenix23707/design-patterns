package by.vsu.kovzov.decorators;

import by.vsu.kovzov.model.Tradable;

public class MarginDecorator extends ProductDecorator {

    private double percent;

    public MarginDecorator(Tradable tradable, double percent) {
        super(tradable);
        this.percent = percent;
    }

    @Override
    public double getPrice() {
        return percent >= 1 ? super.getPrice() * percent : super.getPrice() * (1 + percent);
    }
}
