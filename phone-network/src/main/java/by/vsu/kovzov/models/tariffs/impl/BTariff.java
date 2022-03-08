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

    @Override
    protected Duration getCallBillingTime(CallInfo info) {
        Phone phone = info.getTo().getPhone();
        Duration duration;
        if (isRoaming(phone)) {
            duration = Duration.ofMinutes(1);
        } else {
            duration = Duration.ofSeconds(1);
        }
        return duration;
    }

    @Override
    protected BigDecimal getInnerCallPrice(CallInfo info) {
        Duration duration = getDuration(info.getStart(), info.getEnd());
        if (duration.compareTo(Duration.ofMinutes(1)) <= 0) {

        } else {

        }
        return super.getInnerCallPrice(info);
    }
}
