package by.vsu.models.tariffs;

import by.vsu.models.Phone;
import by.vsu.models.Subscriber;
import by.vsu.models.info.SmsInfo;
import by.vsu.models.tariffs.impl.ATariff;
import by.vsu.models.tariffs.impl.BTariff;
import by.vsu.models.tariffs.impl.GTariff;
import by.vsu.models.tariffs.impl.VTariff;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static by.vsu.Constants.TARIFFS;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
                ),
                // B tariff
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new SmsInfo(sub, sub),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).roamingSmsPrice)
                ),
                // V tariff
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new SmsInfo(sub, sub),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).roamingSmsPrice)
                ),
                // G tariff
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new SmsInfo(sub, sub),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).roamingSmsPrice)
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
                ),
                // B tariff
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new SmsInfo(sub, sub),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).innerSmsPrice)
                ),
                // V tariff
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new SmsInfo(sub, sub),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).innerSmsPrice)
                ),
                // G tariff
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new SmsInfo(sub, sub),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).innerSmsPrice)
                )
        );
    }
}