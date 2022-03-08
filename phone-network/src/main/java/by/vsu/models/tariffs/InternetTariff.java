package by.vsu.models.tariffs;

import by.vsu.models.info.InternetInfo;

import java.math.BigDecimal;

public interface InternetTariff {
    BigDecimal getPrice(InternetInfo info);
}
