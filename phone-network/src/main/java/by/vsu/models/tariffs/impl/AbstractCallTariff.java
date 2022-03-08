package by.vsu.models.tariffs.impl;

import by.vsu.models.Phone;
import by.vsu.models.info.CallInfo;
import by.vsu.models.tariffs.CallTariff;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;

import static by.vsu.utils.TariffUtil.*;

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
        return getNumOfCallPieces(info)
                .multiply(getPriceOneCallPiece(roamingCallPrice, info));
    }

    protected BigDecimal getInnerCallPrice(CallInfo info) {
        return getNumOfCallPieces(info)
                .multiply(getPriceOneCallPiece(innerCallPrice, info));
    }

    protected BigDecimal getOuterCallPrice(CallInfo info) {
        return getNumOfCallPieces(info)
                .multiply(getPriceOneCallPiece(outerCallPrice, info));
    }

    protected BigDecimal getStationaryCallPrice(CallInfo info) {
        return getNumOfCallPieces(info)
                .multiply(getPriceOneCallPiece(stationaryCallPrice, info));
    }

    protected BigDecimal getPriceOneCallPiece(double callPrice,CallInfo info) {
        long billingTime = getCallBillingTime(info).getSeconds();
        BigDecimal numOfPieces = BigDecimal.valueOf(timeOfPriceMeasurement.getSeconds() / billingTime);
        BigDecimal price = BigDecimal.valueOf(callPrice);
        return price.divide(numOfPieces, MathContext.DECIMAL64);
    }

    protected BigDecimal getNumOfCallPieces(CallInfo info) {
        Duration duration = getDuration(info.getStart(), info.getEnd());
        return getNumOfCallPieces(duration, info);
    }

    protected BigDecimal getNumOfCallPieces(Duration duration, CallInfo info) {
        double billingTime = getCallBillingTime(info).getSeconds();

        return BigDecimal.valueOf(Math.ceil(duration.getSeconds() / billingTime));
    }
}
