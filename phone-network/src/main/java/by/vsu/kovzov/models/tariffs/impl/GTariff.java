package by.vsu.kovzov.models.tariffs.impl;

import by.vsu.kovzov.models.info.InternetInfo;

import java.math.BigDecimal;

public class GTariff extends AbstractTariff{

    public GTariff() {
        super(500, 100, 100, 100, 150, 5000);


    }

    @Override
    public BigDecimal getPrice(InternetInfo info) {
        return null;
    }


}
