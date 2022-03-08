package by.vsu.models.operations;

import by.vsu.models.Subscriber;
import by.vsu.models.info.SmsInfo;
import by.vsu.models.tariffs.SmsTariff;
import by.vsu.models.tariffs.Tariff;

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
        Tariff tariff = getOwner().getTariff();
        if (tariff instanceof SmsTariff) {
            return ((SmsTariff) tariff).getPrice(info);
        } else {
            throw new RuntimeException("Tariff " + getOwner().getTariff() + " doesn't support sms operation");
        }
    }

    @Override
    public String toString() {
        return "Sms{" +
                "info=" + info +
                '}';
    }
}
