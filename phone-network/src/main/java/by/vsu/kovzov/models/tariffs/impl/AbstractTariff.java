package by.vsu.kovzov.models.tariffs.impl;

import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.info.SmsInfo;
import by.vsu.kovzov.models.tariffs.CallTariff;
import by.vsu.kovzov.models.tariffs.SmsTariff;

import java.math.BigDecimal;
import java.time.Duration;

import static by.vsu.kovzov.utils.TariffUtil.isRoaming;
import static by.vsu.kovzov.utils.TariffUtil.isInner;
import static by.vsu.kovzov.utils.TariffUtil.isOuter;

public abstract class AbstractTariff implements CallTariff, SmsTariff {
    public final double roamingCallPrice ;
    public final double innerCallPrice ;
    public final double outerCallPrice ;
    public final double stationaryCallPrice ;

    public final double roamingSmsPrice ;
    public final double innerSmsPrice ;


    public AbstractTariff(double roamingCallPrice, double innerCallPrice, double outerCallPrice, double stationaryCallPrice, double roamingSmsPrice, double innerSmsPrice) {
        this.roamingCallPrice = roamingCallPrice;
        this.innerCallPrice = innerCallPrice;
        this.outerCallPrice = outerCallPrice;
        this.stationaryCallPrice = stationaryCallPrice;
        this.roamingSmsPrice = roamingSmsPrice;
        this.innerSmsPrice = innerSmsPrice;
    }

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
        return getPriceCoefficient()
                .multiply(BigDecimal.valueOf(roamingCallPrice))
                .multiply(BigDecimal.valueOf(getBillingPieces(info)));
    }

    protected BigDecimal getInnerCallPrice(CallInfo info) {
        return getPriceCoefficient()
                .multiply(BigDecimal.valueOf(innerCallPrice))
                .multiply(BigDecimal.valueOf(getBillingPieces(info)));
    }

    protected BigDecimal getOuterCallPrice(CallInfo info) {
        return getPriceCoefficient()
                .multiply(BigDecimal.valueOf(outerCallPrice))
                .multiply(BigDecimal.valueOf(getBillingPieces(info)));
    }

    protected BigDecimal getStationaryCallPrice(CallInfo info) {
        return getPriceCoefficient()
                .multiply(BigDecimal.valueOf(stationaryCallPrice))
                .multiply(BigDecimal.valueOf(getBillingPieces(info)));
    }

    @Override
    public BigDecimal getPrice(SmsInfo info) {
        Phone phoneTo = info.getTo().getPhone();
        BigDecimal price;
        if (isRoaming(phoneTo)) {
            price = getRoamingSmsPrice(info);
        } else {
            price = getInnerSmsPrice(info);
        }
        return price;
    }

    protected BigDecimal getRoamingSmsPrice(SmsInfo info) {
        return BigDecimal.valueOf(roamingSmsPrice);
    }

    protected BigDecimal getInnerSmsPrice(SmsInfo info) {
        return BigDecimal.valueOf(innerSmsPrice);
    }

    protected int getBillingPieces(CallInfo info) {
        return (int) Math.ceil(getDuration(info).getSeconds() / ((double) getBillingTime().getSeconds()));
    }

    protected final BigDecimal getPriceCoefficient() {
        return BigDecimal.valueOf(
                getBillingTime().getSeconds() / ((double) Duration.ofMinutes(1).getSeconds())
        );
    }

    protected final Duration getDuration(CallInfo info) {
        return Duration.between(info.getStart().toInstant(), info.getEnd().toInstant());
    }
}
