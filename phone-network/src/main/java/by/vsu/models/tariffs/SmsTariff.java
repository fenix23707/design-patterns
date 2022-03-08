package by.vsu.models.tariffs;

import by.vsu.models.info.SmsInfo;

import java.math.BigDecimal;

public interface SmsTariff {
    BigDecimal getPrice(SmsInfo info);
}
