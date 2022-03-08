package by.vsu.models.tariffs.impl;

import by.vsu.models.info.CallInfo;

import java.time.Duration;

public class VTariff extends BTariff{

    @Override
    protected Duration getCallBillingTime(CallInfo info) {
        return Duration.ofMinutes(1);
    }

    public VTariff() {
        super(1000, 5, 25, 25, 150, 150, 100, 100, 100,1_250,1_000);
    }
}
