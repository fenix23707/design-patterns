package by.vsu.models.operations;

import by.vsu.models.Subscriber;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class Operation implements Serializable {
    private Long id;

    private BigDecimal price;

    public Operation() {
    }

    public Operation(Long id) {
        this.id = id;
    }

    public abstract Subscriber getOwner();

    protected abstract BigDecimal calculatePrice();

    public BigDecimal getPrice() {
        if (price == null) {
            price = calculatePrice();
        }
        return price;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
