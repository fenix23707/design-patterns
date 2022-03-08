package by.vsu.models.tariffs;

import by.vsu.models.Phone;
import by.vsu.models.Subscriber;
import by.vsu.models.info.InternetInfo;
import by.vsu.models.tariffs.impl.ATariff;
import by.vsu.models.tariffs.impl.BTariff;
import by.vsu.models.tariffs.impl.GTariff;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Stream;

import static by.vsu.Constants.TARIFFS;
import static org.junit.jupiter.api.Assertions.*;

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
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).firstPartInternetPrice)
                ),
                Arguments.of(
                        TARIFFS.get(BTariff.class),
                        new InternetInfo(owner, 100, start, new Date(date + 60 * 60 * 1000)),
                        BigDecimal.valueOf(((BTariff) TARIFFS.get(BTariff.class)).firstPartInternetPrice + ((BTariff) TARIFFS.get(BTariff.class)).internetPrice)
                )

        );
    }
}