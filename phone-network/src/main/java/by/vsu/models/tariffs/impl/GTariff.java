package by.vsu.models.tariffs.impl;

import by.vsu.models.info.CallInfo;
import by.vsu.models.info.InternetInfo;

import java.math.BigDecimal;
import java.time.Duration;

public class GTariff extends BTariff{
    public final double midlPartInternetPrice;


    public GTariff() {
        super(500, 100, 100, 100, 5_000, 150, 500, 500, 500, 5000, 100);
        this.midlPartInternetPrice = 1_000;
    }

    @Override
    protected Duration getCallBillingTime(CallInfo info) {
        return Duration.ofSeconds(10);
    }

    @Override
    public BigDecimal getPrice(InternetInfo info) {
        BigDecimal price;
        long totalTraffic = info.getTraffic();
        long firstPartTraffic = totalTraffic;
        if (totalTraffic > 50) {
            firstPartTraffic = 50;
        }
        price = BigDecimal.valueOf(firstPartTraffic * firstPartInternetPrice);

        totalTraffic -= firstPartTraffic;
        firstPartTraffic = firstPartTraffic;
        if (totalTraffic > 400) {
            firstPartTraffic = 400;
        }
        totalTraffic -= firstPartTraffic;
        price = price.add(BigDecimal.valueOf(firstPartTraffic * midlPartInternetPrice));

        return price.add(BigDecimal.valueOf(totalTraffic * internetPrice));
    }
}
