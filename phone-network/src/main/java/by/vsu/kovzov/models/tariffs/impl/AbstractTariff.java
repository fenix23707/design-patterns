package by.vsu.kovzov.models.tariffs.impl;

import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.info.SmsInfo;
import by.vsu.kovzov.models.tariffs.SmsTariff;

import java.math.BigDecimal;

import static by.vsu.kovzov.utils.TariffUtil.isRoaming;

public abstract class AbstractTariff extends AbstractCallTariff implements SmsTariff {
    public final double roamingSmsPrice;
    public final double innerSmsPrice;

    public AbstractTariff(double roamingCallPrice, double innerCallPrice, double outerCallPrice, double stationaryCallPrice, double roamingSmsPrice, double innerSmsPrice) {
        super(roamingCallPrice, innerCallPrice, outerCallPrice, stationaryCallPrice);
        this.roamingSmsPrice = roamingSmsPrice;
        this.innerSmsPrice = innerSmsPrice;
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

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
