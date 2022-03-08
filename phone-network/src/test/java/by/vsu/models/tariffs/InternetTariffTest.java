package by.vsu.models.tariffs;

import by.vsu.models.Phone;
import by.vsu.models.Subscriber;
import by.vsu.models.info.InternetInfo;
import by.vsu.models.tariffs.impl.ATariff;
import by.vsu.models.tariffs.impl.BTariff;
import by.vsu.models.tariffs.impl.GTariff;
import by.vsu.models.tariffs.impl.VTariff;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Stream;

import static by.vsu.Constants.TARIFFS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InternetTariffTest {

    @ParameterizedTest
    @MethodSource("differentDurationInternetData")
    void differentDurationGetPriceTest(InternetTariff tariff, InternetInfo info, BigDecimal expected) {
        BigDecimal actual = tariff.getPrice(info);
        assertEquals(expected.doubleValue(), actual.doubleValue(), 0.000001);
    }

    private static Stream<Arguments> differentDurationInternetData() {
        Phone phone = new Phone("00370", "00", "0000000");
        Subscriber owner = new Subscriber(phone, null);
        Date start = new Date();
        long date = start.getTime();
        return Stream.of(
                // A tariff
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new InternetInfo(owner, 100, start, new Date(date + 60 * 60 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).internetPrice)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new InternetInfo(owner, 100, start, new Date(date + 45 * 60 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).internetPrice * 45 / 60)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new InternetInfo(owner, 100, start, new Date(date + 30 * 60 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).internetPrice * 30 / 60)
                ),
                Arguments.of(
                        TARIFFS.get(ATariff.class),
                        new InternetInfo(owner, 100, start, new Date(date + 120 * 60 * 1000)),
                        BigDecimal.valueOf(((ATariff) TARIFFS.get(ATariff.class)).internetPrice *  120 / 60)
                ),
                // B tariff
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new InternetInfo(owner, 50, start, new Date(date + 60 * 60 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).firstPartInternetPrice * 50)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new InternetInfo(owner, 100, start, new Date(date + 60 * 60 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).firstPartInternetPrice * 50 + ((BTariff) TARIFFS.get(BTariff.class)).internetPrice*50)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new InternetInfo(owner, 203, start, new Date(date + 60 * 60 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).firstPartInternetPrice * 50 + ((BTariff) TARIFFS.get(BTariff.class)).internetPrice*153)
                ),
                // V tariff
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new InternetInfo(owner, 50, start, new Date(date + 60 * 60 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).firstPartInternetPrice * 50)
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new InternetInfo(owner, 100, start, new Date(date + 60 * 60 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).firstPartInternetPrice * 50 + ((VTariff) TARIFFS.get(VTariff.class)).internetPrice*50)
                ),
                Arguments.of(
                        TARIFFS.get(VTariff.class),
                        new InternetInfo(owner, 203, start, new Date(date + 60 * 60 * 1000)),
                        BigDecimal.valueOf(((VTariff) TARIFFS.get(VTariff.class)).firstPartInternetPrice * 50 + ((VTariff) TARIFFS.get(VTariff.class)).internetPrice*153)
                ),
                // G tariff
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new InternetInfo(owner, 50, start, new Date(date + 60 * 60 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).firstPartInternetPrice * 50)
                ),
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new InternetInfo(owner, 120, start, new Date(date + 60 * 60 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).firstPartInternetPrice * 100 + ((GTariff) TARIFFS.get(GTariff.class)).midlPartInternetPrice*20)
                ),
                Arguments.of(
                        TARIFFS.get(GTariff.class),
                        new InternetInfo(owner, 503, start, new Date(date + 60 * 60 * 1000)),
                        BigDecimal.valueOf(((GTariff) TARIFFS.get(GTariff.class)).firstPartInternetPrice * 100 + ((GTariff) TARIFFS.get(GTariff.class)).midlPartInternetPrice * 400 +((GTariff) TARIFFS.get(GTariff.class)).internetPrice*3)
                )

        );
    }
}