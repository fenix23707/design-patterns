package by.vsu.kovzov.models.tariffs.impl;

import by.vsu.kovzov.models.info.InternetInfo;

import java.math.BigDecimal;

public class BTariff extends AbstractTariff{

    public BTariff() {
        super(5000, 50, 500, 250, 150, 1000);
    }

    @Override
    public BigDecimal getPrice(InternetInfo info) {
        return null;
    }
}
