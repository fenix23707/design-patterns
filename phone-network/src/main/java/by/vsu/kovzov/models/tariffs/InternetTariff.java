package by.vsu.kovzov.models.tariffs;

import by.vsu.kovzov.models.info.InternetInfo;

import java.math.BigDecimal;

public interface InternetTariff {
    BigDecimal getPrice(InternetInfo info);
}
