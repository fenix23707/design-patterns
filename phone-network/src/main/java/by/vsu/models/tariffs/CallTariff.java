package by.vsu.models.tariffs;

import by.vsu.models.info.CallInfo;

import java.math.BigDecimal;

public interface CallTariff extends Tariff {
    BigDecimal getPrice(CallInfo info);
}
