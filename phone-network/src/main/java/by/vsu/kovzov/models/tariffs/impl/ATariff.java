package by.vsu.kovzov.models.tariffs.impl;

import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.info.InternetInfo;
import by.vsu.kovzov.models.tariffs.InternetTariff;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;

import static by.vsu.kovzov.utils.TariffUtil.getDuration;

public class ATariff extends AbstractTariff implements InternetTariff {
    public final double internetPrice = 3_000;

    @Override
    protected Duration getCallBillingTime(CallInfo info) {
        return Duration.ofSeconds(10);
    }

    public ATariff() {
        super(2500, 25, 125, 95, 1000, 150);
    }

    @Override
    public BigDecimal getPrice(InternetInfo info) {
        return BigDecimal.valueOf(internetPrice)
                .multiply(getInternetBillingPieces(info));
    }

    protected BigDecimal getInternetBillingPieces(InternetInfo info) {
        long duration = getDuration(info.getStart(), info.getEnd()).getSeconds();
        double billingTime = ((double) getInternetBillingTime().getSeconds());

        return BigDecimal.valueOf(duration / billingTime);
    }

    protected Duration getInternetBillingTime() {
        return Duration.ofHours(1);
    }
}
