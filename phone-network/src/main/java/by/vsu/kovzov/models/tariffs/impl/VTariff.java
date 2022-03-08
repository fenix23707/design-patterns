package by.vsu.kovzov.models.tariffs.impl;

import by.vsu.kovzov.models.info.InternetInfo;

import java.math.BigDecimal;

public class VTariff extends AbstractTariff{
    public VTariff() {
        super(1000, 5, 25, 25, 150, 150);
    }

    @Override
    public BigDecimal getPrice(InternetInfo info) {
        return null;
    }
}
