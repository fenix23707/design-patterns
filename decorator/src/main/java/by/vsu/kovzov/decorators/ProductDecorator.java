package by.vsu.kovzov.decorators;

import by.vsu.kovzov.model.Tradable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductDecorator implements Tradable {
    private Tradable tradable;

    @Override
    public double getPrice() {
        return tradable.getPrice();
    }

    @Override
    public final String getCategory() {
        return tradable.getCategory();
    }

    @Override
    public final String getName() {
        return tradable.getName();
    }
}
