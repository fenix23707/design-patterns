package by.vsu.kovzov.utils;

import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.info.CallInfo;

import java.math.BigDecimal;
import java.time.Duration;

import static by.vsu.kovzov.Constants.OUTER_OPERATOR_CODES;

public class CallTariffUtil {

    public static boolean isInnerCall(Phone phone) {
        return "00375".equals(phone.getCountryCode()) && "55".equals(phone.getOperatorCode());
    }

    public static boolean isOuterCall(Phone phone) {
        return "00375".equals(phone.getCountryCode())
                && OUTER_OPERATOR_CODES.contains(phone.getOperatorCode());
    }

    public static boolean isRoaming(Phone phone) {
        return !"00375".equals(phone.getCountryCode());
    }
}
