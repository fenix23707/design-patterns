package by.vsu.kovzov.models.operations;

import by.vsu.kovzov.models.Subscriber;
import by.vsu.kovzov.models.info.InternetInfo;
import by.vsu.kovzov.models.tariffs.InternetTariff;
import by.vsu.kovzov.models.tariffs.Tariff;

import java.math.BigDecimal;

public class InternetOperation extends Operation{
    private InternetInfo info;

    public InternetOperation(InternetInfo info) {
        this.info = info;
    }

    @Override
    public Subscriber getOwner() {
        return info.getOwner();
    }

    @Override
    protected BigDecimal calculatePrice() {
        Tariff tariff = info.getOwner().getTariff();
        if (tariff instanceof InternetTariff) {
            return ((InternetTariff) tariff).getPrice(info);
        } else {
            throw new RuntimeException("Tariff " + info.getOwner().getTariff() + " doesn't support internet operation");
        }

    }

    @Override
    public String toString() {
        return "Internet{" +
                "info=" + info +
                '}';
    }
}
