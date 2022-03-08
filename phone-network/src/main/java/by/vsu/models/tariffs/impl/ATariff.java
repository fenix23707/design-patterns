package by.vsu.models.tariffs.impl;

import by.vsu.models.info.CallInfo;
import by.vsu.models.info.InternetInfo;
import by.vsu.models.tariffs.InternetTariff;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;

import static by.vsu.utils.TariffUtil.getDuration;

public class ATariff extends AbstractTariff implements InternetTariff {
    private final Duration timeOfPriceMeasurement;

    public final double internetPrice;

    @Override
    protected Duration getCallBillingTime(CallInfo info) {
        return Duration.ofSeconds(10);
    }

    public ATariff() {
        super(2500, 25, 125, 95, 1000, 150);

        this.internetPrice = 3_000;
        this.timeOfPriceMeasurement = Duration.ofHours(1);
    }

    @Override
    public BigDecimal getPrice(InternetInfo info) {
        return BigDecimal.valueOf(internetPrice)
                .multiply(getNumOfInternetPieces(info), MathContext.DECIMAL64);
    }

    protected BigDecimal getNumOfInternetPieces(InternetInfo info) {
        Duration duration = getDuration(info.getStart(), info.getEnd());
        return getNumOfInternetPieces(duration);
    }

    private BigDecimal getNumOfInternetPieces(Duration duration) {
        double dur = duration.getSeconds();
        double topm = timeOfPriceMeasurement.getSeconds();
        return BigDecimal.valueOf(dur / topm);
    }
}
