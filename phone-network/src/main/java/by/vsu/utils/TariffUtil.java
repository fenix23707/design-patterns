package by.vsu.utils;

import by.vsu.Constants;
import by.vsu.models.Phone;

import java.time.Duration;
import java.util.Date;

public class TariffUtil {

    public static boolean isInner(Phone phone) {
        return "00375".equals(phone.getCountryCode()) && "55".equals(phone.getOperatorCode());
    }

    public static boolean isOuter(Phone phone) {
        return "00375".equals(phone.getCountryCode())
                && Constants.OUTER_OPERATOR_CODES.contains(phone.getOperatorCode());
    }

    public static boolean isRoaming(Phone phone) {
        return !"00375".equals(phone.getCountryCode());
    }

    public static Duration getDuration(Date start, Date end) {
        return Duration.between(start.toInstant(), end.toInstant());
    }
}
