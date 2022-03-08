package by.vsu.kovzov.models.tariffs;

import by.vsu.kovzov.models.info.CallInfo;

import java.math.BigDecimal;
import java.time.Duration;

public interface CallTariff extends Tariff {
    BigDecimal getPrice(CallInfo info);

    default Duration getCallBillingTime() {
        return Duration.ofMinutes(1);
    }
}
