package by.vsu.kovzov.model;

import lombok.Data;

@Data
public class Discount {
    String category;
    Type type;
    double value;

    /**
     * discount measures in currency or in percent
     */
    public enum Type {
        PERCENT, CURRENCY
    }
}
