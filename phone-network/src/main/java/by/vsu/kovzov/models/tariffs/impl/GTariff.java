package by.vsu.kovzov.models.tariffs.impl;

import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.info.InternetInfo;

import java.math.BigDecimal;
import java.time.Duration;

public class GTariff extends BTariff{

    @Override
    protected Duration getCallBillingTime(CallInfo info) {
        return Duration.ofMinutes(1);
    }

    public GTariff() {
        super(1000, 5, 25, 25, 150, 150, 100, 100, 100,1_250,1_000);
    }
}
