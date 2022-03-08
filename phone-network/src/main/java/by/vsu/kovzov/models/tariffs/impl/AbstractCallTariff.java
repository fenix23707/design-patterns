package by.vsu.kovzov.models.tariffs.impl;

import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.tariffs.CallTariff;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;

import static by.vsu.kovzov.utils.TariffUtil.*;

public abstract class AbstractCallTariff implements CallTariff {
    private final Duration timeOfPriceMeasurement;

    public final double roamingCallPrice;
    public final double innerCallPrice;
    public final double outerCallPrice;
    public final double stationaryCallPrice;

    public AbstractCallTariff(double roamingCallPrice, double innerCallPrice, double outerCallPrice, double stationaryCallPrice) {
        this.roamingCallPrice = roamingCallPrice;
        this.innerCallPrice = innerCallPrice;
        this.outerCallPrice = outerCallPrice;
        this.stationaryCallPrice = stationaryCallPrice;
        this.timeOfPriceMeasurement = Duration.ofMinutes(1);
    }

    protected abstract Duration getCallBillingTime(CallInfo info);

    @Override
    public BigDecimal getPrice(CallInfo info) {
        Phone phoneTo = info.getTo().getPhone();
        BigDecimal price;
        if (isRoaming(phoneTo)) {
            price = getRoamingCallPrice(info);
        } else if (isInner(phoneTo)) {
            price = getInnerCallPrice(info);
        } else if (isOuter(phoneTo)) {
            price = getOuterCallPrice(info);
        } else {
            price = getStationaryCallPrice(info);
        }
        return price;
    }

    protected BigDecimal getRoamingCallPrice(CallInfo info) {
        return getCallPriceCoefficient(info)
                .multiply(BigDecimal.valueOf(roamingCallPrice))
                .multiply(getCallBillingPieces(info));
    }

    protected BigDecimal getInnerCallPrice(CallInfo info) {
        return getCallPriceCoefficient(info)
                .multiply(BigDecimal.valueOf(innerCallPrice))
                .multiply(getCallBillingPieces(info));
    }

    protected BigDecimal getOuterCallPrice(CallInfo info) {
        return getCallPriceCoefficient(info)
                .multiply(BigDecimal.valueOf(outerCallPrice))
                .multiply(getCallBillingPieces(info));
    }

    protected BigDecimal getStationaryCallPrice(CallInfo info) {
        return getCallPriceCoefficient(info)
                .multiply(BigDecimal.valueOf(stationaryCallPrice))
                .multiply(getCallBillingPieces(info));
    }




    protected BigDecimal getCallBillingPieces(CallInfo info) {
        double duration = getDuration(info.getStart(), info.getEnd()).getSeconds();
        double billingTime = getCallBillingTime(info).getSeconds();

        return BigDecimal.valueOf(Math.ceil(duration / billingTime));
    }

    protected final BigDecimal getCallPriceCoefficient(CallInfo info ) {
        return BigDecimal.valueOf(
                getCallBillingTime(info).getSeconds() / ((double) Duration.ofMinutes(1).getSeconds())
        );
    }

}
