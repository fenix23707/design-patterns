package by.vsu.kovzov.models.tariffs;

import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.Subscriber;
import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.tariffs.impl.ATariff;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static by.vsu.kovzov.models.tariffs.impl.ATariff.INNER_PRICE;
import static org.junit.jupiter.api.Assertions.*;

class CallTariffTest {
    private static final Map<Class<? extends CallTariff>, CallTariff> tariffs = new HashMap<>();

    @BeforeAll
    static void beforeAll() {
        tariffs.put(ATariff.class, new ATariff());
    }

    @ParameterizedTest
    @MethodSource("innerCallData")
    void innerCallGetPriceTest(CallTariff tariff, CallInfo info, BigDecimal expected) {
        BigDecimal actual = tariff.getPrice(info);
        assertEquals(expected.doubleValue(), actual.doubleValue(), 0.000001);
    }

    private static Stream<Arguments> innerCallData() {
        Phone phone = new Phone("00375", "55", "0000000");
        Subscriber sub = new Subscriber(phone, null);
        Date start = new Date();
        long date = start.getTime();
        return Stream.of(
                // A tariff
                Arguments.of(
                        tariffs.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(INNER_PRICE)
                ),

                Arguments.of(
                        tariffs.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 61 * 1000)),
                        BigDecimal.valueOf(INNER_PRICE / 6 * 7)
                ),

                Arguments.of(
                        tariffs.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 4 * 1000)),
                        BigDecimal.valueOf(INNER_PRICE / 6 * 1)
                )
        );
    }
}