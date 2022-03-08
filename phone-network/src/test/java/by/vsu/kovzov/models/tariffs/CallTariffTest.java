package by.vsu.kovzov.models.tariffs;

import by.vsu.kovzov.models.Phone;
import by.vsu.kovzov.models.Subscriber;
import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.tariffs.impl.ATariff;
import by.vsu.kovzov.models.tariffs.impl.AbstractTariff;
import by.vsu.kovzov.models.tariffs.impl.BTariff;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Stream;

import static by.vsu.kovzov.Constants.TARIFFS;
import static org.junit.jupiter.api.Assertions.*;

class CallTariffTest {
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
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((ATariff)TARIFFS.get(ATariff.class)).innerCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 61 * 1000)),
                        BigDecimal.valueOf(((ATariff)TARIFFS.get(ATariff.class)).innerCallPrice / 6 * 7)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 4 * 1000)),
                        BigDecimal.valueOf(((ATariff)TARIFFS.get(ATariff.class)).innerCallPrice / 6 * 1)
                ),

                // B tariff
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((BTariff)TARIFFS.get(BTariff.class)).innerFirstMinCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 61 * 1000)),
                        BigDecimal.valueOf(((BTariff)TARIFFS.get(BTariff.class)).innerFirstMinCallPrice + ((BTariff)TARIFFS.get(BTariff.class)).innerCallPrice / 6)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 4 * 1000)),
                        BigDecimal.valueOf(((BTariff)TARIFFS.get(BTariff.class)).innerFirstMinCallPrice / 6)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 120 * 1000)),
                        BigDecimal.valueOf(((BTariff)TARIFFS.get(BTariff.class)).innerFirstMinCallPrice  + ((BTariff)TARIFFS.get(BTariff.class)).innerCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 123 * 1000)),
                        BigDecimal.valueOf(((BTariff)TARIFFS.get(BTariff.class)).innerFirstMinCallPrice  + ((BTariff)TARIFFS.get(BTariff.class)).innerCallPrice / 6 * 7)
                )

                //
        );
    }
}