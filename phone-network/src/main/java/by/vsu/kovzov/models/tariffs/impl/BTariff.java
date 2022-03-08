package by.vsu.kovzov.models.tariffs.impl;

import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.info.InternetInfo;

import java.math.BigDecimal;
import java.time.Duration;

import static by.vsu.kovzov.utils.TariffUtil.getDuration;
import static by.vsu.kovzov.utils.TariffUtil.isRoaming;

public class BTariff extends AbstractTariff{

    public final double innerFirstMinCallPrice;
    public final double outerFirstMinCallPrice;
    public final double stationaryFirstMinCallPrice;

    public BTariff() {
        super(5000, 50, 500, 250, 150, 1000);
        this.innerFirstMinCallPrice = 5;
        this.outerFirstMinCallPrice = 75;
        this.stationaryFirstMinCallPrice = 50;
    }

    public BTariff(double roamingCallPrice, double innerCallPrice, double outerCallPrice, double stationaryCallPrice, double roamingSmsPrice, double innerSmsPrice, double innerFirstMinCallPrice, double outerFirstMinCallPrice, double stationaryFirstMinCallPrice) {
        super(roamingCallPrice, innerCallPrice, outerCallPrice, stationaryCallPrice, roamingSmsPrice, innerSmsPrice);
        this.innerFirstMinCallPrice = innerFirstMinCallPrice;
        this.outerFirstMinCallPrice = outerFirstMinCallPrice;
        this.stationaryFirstMinCallPrice = stationaryFirstMinCallPrice;
    }

    @Override
    protected Duration getCallBillingTime(CallInfo info) {
        Phone phone = info.getTo().getPhone();
        Duration duration;
        if (isRoaming(phone)) {
            duration = Duration.ofMinutes(1);
        } else {
            duration = Duration.ofSeconds(10);
        }
        return duration;
    }

    @Override
    protected BigDecimal getInnerCallPrice(CallInfo info) {
        return getCallPrice(innerCallPrice, innerFirstMinCallPrice, info);
    }

    @Override
    protected BigDecimal getOuterCallPrice(CallInfo info) {
        return getCallPrice(outerCallPrice, outerFirstMinCallPrice, info);
    }

    @Override
    protected BigDecimal getStationaryCallPrice(CallInfo info) {
        return getCallPrice(stationaryCallPrice, stationaryFirstMinCallPrice, info);
    }

    private BigDecimal getCallPrice(double callPrice, double firstMinCallPrice, CallInfo info) {
        BigDecimal price;
        Duration totalDuration = getDuration(info.getStart(), info.getEnd());
        Duration firstMinDuration = totalDuration;
        if (totalDuration.compareTo(Duration.ofMinutes(1)) > 0) {
            firstMinDuration = Duration.ofMinutes(1);
        }
        Duration duration = totalDuration.minus(firstMinDuration);

        price = getNumOfCallPieces(firstMinDuration, info)
                .multiply(getPriceOneCallPiece(firstMinCallPrice, info));
        return price.add(
                getNumOfCallPieces(duration, info)
                        .multiply(getPriceOneCallPiece(callPrice, info))
        );
    }
}
