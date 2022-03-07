package by.vsu.kovzov.models.tariffs;

import by.vsu.kovzov.models.info.SmsInfo;

import java.math.BigDecimal;

public interface SmsTariff {
    BigDecimal getPrice(SmsInfo info);
}
