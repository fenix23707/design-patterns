package by.vsu.kovzov.decorators;

import by.vsu.kovzov.model.Discount;
import by.vsu.kovzov.model.Tradable;

public class DiscountDecorator extends ProductDecorator {
    private Discount discount;

    public DiscountDecorator(Tradable tradable, Discount discount) {
        super(tradable);
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return super.getPrice() - getDiscount();
    }

    private double getDiscount() {
        switch (discount.getType()) {
            case CURRENCY: return discount.getValue();
            case PERCENT: return discount.getValue() * super.getPrice();
            default: throw new RuntimeException("unknown discount type");
        }
    }
}
