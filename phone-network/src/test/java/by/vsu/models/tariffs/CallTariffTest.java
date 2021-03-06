package by.vsu.models.tariffs;

import by.vsu.models.Phone;
import by.vsu.models.Subscriber;
import by.vsu.models.info.CallInfo;
import by.vsu.models.tariffs.impl.ATariff;
import by.vsu.models.tariffs.impl.BTariff;
import by.vsu.models.tariffs.impl.GTariff;
import by.vsu.models.tariffs.impl.VTariff;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

import static by.vsu.Constants.OUTER_OPERATOR_CODES;
import static by.vsu.Constants.TARIFFS;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).innerCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 61 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).innerCallPrice / 6 * 7)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 4 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).innerCallPrice / 6 * 1)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 23 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).innerCallPrice / 6 * 3)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 29 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).innerCallPrice / 6 * 3)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 31 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).innerCallPrice / 6 * 4)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 901 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).innerCallPrice / 6 * 91)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 2000 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).innerCallPrice / 6 * 200)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 11 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).innerCallPrice / 6 * 2)
                ),

                // B tariff
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 61 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice + ((BTariff) TARIFFS.get(BTariff.class)).innerCallPrice / 6)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 4 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice / 6)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 120 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice + ((BTariff) TARIFFS.get(BTariff.class)).innerCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 123 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice + ((BTariff) TARIFFS.get(BTariff.class)).innerCallPrice / 6 * 7)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 190 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice + ((BTariff) TARIFFS.get(BTariff.class)).innerCallPrice / 6 * 13)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 2000 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice + ((BTariff) TARIFFS.get(BTariff.class)).innerCallPrice / 6 * 194)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 20 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice / 6 * 2)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 51 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 36 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice / 6 * 4)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 93 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice + ((BTariff) TARIFFS.get(BTariff.class)).innerCallPrice / 6 * 4)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 155 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice + ((BTariff) TARIFFS.get(BTariff.class)).innerCallPrice / 6 * 10)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 42 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerFirstMinCallPrice / 6 * 5)
                ),

                //V tariff
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).innerFirstMinCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 31 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).innerFirstMinCallPrice )
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 120 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).innerFirstMinCallPrice + ((VTariff) TARIFFS.get(VTariff.class)).innerCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 130 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).innerFirstMinCallPrice + ((VTariff) TARIFFS.get(VTariff.class)).innerCallPrice*2)
                ),

                //G tariff
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).innerFirstMinCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 2000 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).innerFirstMinCallPrice + ((GTariff) TARIFFS.get(GTariff.class)).innerCallPrice / 6 * 194)
                ),
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 20 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).innerFirstMinCallPrice / 6 * 2)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("outerCallData")
    void outerCallGetPriceTest(CallTariff tariff, CallInfo info, BigDecimal expected) {
        BigDecimal actual = tariff.getPrice(info);
        assertEquals(expected.doubleValue(), actual.doubleValue(), 0.000001);
    }

    private static Stream<Arguments> outerCallData() {
        Random random = new Random();
        Phone phone = new Phone("00375", OUTER_OPERATOR_CODES.get(random.nextInt(OUTER_OPERATOR_CODES.size())), "0000000");
        Subscriber sub = new Subscriber(phone, null);
        Date start = new Date();
        long date = start.getTime();
        return Stream.of(
                // A tariff
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).outerCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 4 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).outerCallPrice / 6 * 1)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 901 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).outerCallPrice / 6 * 91)
                ),
                // B tariff
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 61 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).outerFirstMinCallPrice + ((BTariff) TARIFFS.get(BTariff.class)).outerCallPrice / 6)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 155 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).outerFirstMinCallPrice + ((BTariff) TARIFFS.get(BTariff.class)).outerCallPrice / 6 * 10)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 4 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).outerFirstMinCallPrice / 6)
                ),
                //V tariff
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).outerFirstMinCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 31 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).outerFirstMinCallPrice )
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 120 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).outerFirstMinCallPrice + ((VTariff) TARIFFS.get(VTariff.class)).outerCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 130 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).outerFirstMinCallPrice + ((VTariff) TARIFFS.get(VTariff.class)).outerCallPrice*2)
                ),

                //G tariff
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).outerFirstMinCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 2000 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).outerFirstMinCallPrice + ((GTariff) TARIFFS.get(GTariff.class)).outerCallPrice / 6 * 194)
                ),
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 20 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).outerFirstMinCallPrice / 6 * 2)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("stationaryCallData")
    void stationaryCallGetPriceTest(CallTariff tariff, CallInfo info, BigDecimal expected) {
        BigDecimal actual = tariff.getPrice(info);
        assertEquals(expected.doubleValue(), actual.doubleValue(), 0.000001);
    }

    private static Stream<Arguments> stationaryCallData() {
        Random random = new Random();
        Phone phone = new Phone("00375", "00", "0000000");
        Subscriber sub = new Subscriber(phone, null);
        Date start = new Date();
        long date = start.getTime();
        return Stream.of(
                // A tariff
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).stationaryCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 4 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).stationaryCallPrice / 6 * 1)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 901 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).stationaryCallPrice / 6 * 91)
                ),
                // B tariff
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 61 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).stationaryFirstMinCallPrice + ((BTariff) TARIFFS.get(BTariff.class)).stationaryCallPrice / 6)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 155 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).stationaryFirstMinCallPrice + ((BTariff) TARIFFS.get(BTariff.class)).stationaryCallPrice / 6 * 10)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 4 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).stationaryFirstMinCallPrice / 6)
                ),
                //V tariff
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).stationaryFirstMinCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 31 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).stationaryFirstMinCallPrice )
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 120 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).stationaryFirstMinCallPrice + ((VTariff) TARIFFS.get(VTariff.class)).stationaryCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 130 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).stationaryFirstMinCallPrice + ((VTariff) TARIFFS.get(VTariff.class)).stationaryCallPrice*2)
                ),

                //G tariff
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).stationaryFirstMinCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 2000 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).stationaryFirstMinCallPrice + ((GTariff) TARIFFS.get(GTariff.class)).stationaryCallPrice/ 6 * 194)
                ),
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 20 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).stationaryFirstMinCallPrice / 6 * 2)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("roamingCallData")
    void roamingCallGetPriceTest(CallTariff tariff, CallInfo info, BigDecimal expected) {
        BigDecimal actual = tariff.getPrice(info);
        assertEquals(expected.doubleValue(), actual.doubleValue(), 0.000001);
    }

    private static Stream<Arguments> roamingCallData() {
        Random random = new Random();
        Phone phone = new Phone("00370", "00", "0000000");
        Subscriber sub = new Subscriber(phone, null);
        Date start = new Date();
        long date = start.getTime();
        return Stream.of(
                // A tariff
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).roamingCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 4 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).roamingCallPrice / 6 * 1)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 901 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).roamingCallPrice / 6 * 91)
                ),
                // B tariff
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).roamingCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 31 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).roamingCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 120 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).roamingCallPrice * 2)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 130 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).roamingCallPrice * 3)
                ),
                //V tariff
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).roamingCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 31 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).roamingCallPrice )
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 120 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).roamingCallPrice * 2 )
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 130 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).roamingCallPrice * 3 )
                ),

                //G tariff
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 60 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).roamingCallPrice)
                ),
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 2000 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).roamingCallPrice/ 6 * 200)
                ),
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new CallInfo(sub, sub, start, new Date(date + 20 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).roamingCallPrice / 6 * 2)
                )
        );
    }
}