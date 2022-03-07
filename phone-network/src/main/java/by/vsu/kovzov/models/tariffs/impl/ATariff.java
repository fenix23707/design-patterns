package by.vsu.kovzov.models.tariffs.impl;

import java.time.Duration;

public class ATariff extends AbstractTariff{
    @Override
    public Duration getBillingTime() {
        return Duration.ofSeconds(10);
    }

    public ATariff() {
        super(2500, 25, 125, 95);
    }
}
