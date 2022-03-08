package by.vsu.kovzov.models.operations;

import by.vsu.kovzov.models.Subscriber;
import by.vsu.kovzov.models.info.SmsInfo;
import by.vsu.kovzov.models.tariffs.CallTariff;
import by.vsu.kovzov.models.tariffs.SmsTariff;
import by.vsu.kovzov.models.tariffs.Tariff;

import java.math.BigDecimal;

public class SmsOperation extends Operation{
    private SmsInfo info;

    public SmsOperation(SmsInfo info) {
        this.info = info;
    }

    public SmsOperation(Long id, SmsInfo info) {
        super(id);
        this.info = info;
    }

    @Override
    public Subscriber getOwner() {
        return info.getFrom();
    }

    @Override
    protected BigDecimal calculatePrice() {
        Tariff tariff = info.getFrom().getTariff();
        if (tariff instanceof SmsTariff) {
            return ((SmsTariff) tariff).getPrice(info);
        } else {
            throw new RuntimeException("Tariff " + info.getFrom().getTariff() + " doesn't support sms operation");
        }
    }

    @Override
    public String toString() {
        return "Sms{" +
                "info=" + info +
                '}';
    }
}
