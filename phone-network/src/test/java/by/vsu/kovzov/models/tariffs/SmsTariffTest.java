package by.vsu.kovzov.models.tariffs;

import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.Subscriber;
import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.info.SmsInfo;
import by.vsu.kovzov.models.tariffs.impl.ATariff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Stream;

import static by.vsu.kovzov.Constants.TARIFFS;
import static org.junit.jupiter.api.Assertions.*;

class SmsTariffTest {

    @ParameterizedTest
    @MethodSource("roamingSmsData")
    void roamingSmsGetPriceTest(SmsTariff tariff, SmsInfo info, BigDecimal expected) {
        BigDecimal actual = tariff.getPrice(info);
        assertEquals(expected.doubleValue(), actual.doubleValue(), 0.000001);
    }

    private static Stream<Arguments> roamingSmsData() {
        Phone phone = new Phone("00371", "55", "0000000");
        Subscriber sub = new Subscriber(phone, null);
        return Stream.of(
                // A tariff
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new SmsInfo(sub, sub),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).roamingSmsPrice)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("innerSmsData")
    void innerSmsGetPriceTest(SmsTariff tariff, SmsInfo info, BigDecimal expected) {
        BigDecimal actual = tariff.getPrice(info);
        assertEquals(expected.doubleValue(), actual.doubleValue(), 0.000001);
    }

    private static Stream<Arguments> innerSmsData() {
        Phone phone = new Phone("00375", "55", "0000000");
        Subscriber sub = new Subscriber(phone, null);
        return Stream.of(
                // A tariff
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new SmsInfo(sub, sub),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).innerSmsPrice)
                )
        );
    }


}