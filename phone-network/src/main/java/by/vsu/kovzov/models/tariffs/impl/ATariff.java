package by.vsu.kovzov.models.tariffs.impl;

import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.tariffs.CallTariff;

import java.math.BigDecimal;
import java.time.Duration;

import static by.vsu.kovzov.utils.CallTariffUtil.isRoaming;
import static by.vsu.kovzov.utils.CallTariffUtil.isInnerCall;
import static by.vsu.kovzov.utils.CallTariffUtil.isOuterCall;

public class ATariff implements CallTariff {
    public static final double INNER_PRICE = 25;

    @Override
    public Duration getBillingTime() {
        return Duration.ofSeconds(10);
    }

    @Override
    public BigDecimal getPrice(CallInfo info) {
        Phone phoneTo = info.getTo().getPhone();
        if (isRoaming(phoneTo)) {

        } else if (isInnerCall(phoneTo)) {
            return getInnerCallPrice(info);
        } else if (isOuterCall(phoneTo)) {

        } else {

        }
        return null;
    }

    protected BigDecimal getInnerCallPrice(CallInfo info) {
        return getOnePiecePrice().multiply(BigDecimal.valueOf(getBillingPieces(info)));
    }

    protected int getBillingPieces(CallInfo info) {
        return (int) Math.ceil(getDuration(info).getSeconds() / ((double) getBillingTime().getSeconds()));

    }

    protected BigDecimal getOnePiecePrice() {
        return BigDecimal.valueOf(
                INNER_PRICE * getBillingTime().getSeconds() / ((double) Duration.ofMinutes(1).getSeconds())
        );
    }

    protected Duration getDuration(CallInfo info) {
        return Duration.between(info.getStart().toInstant(), info.getEnd().toInstant());
    }
}
