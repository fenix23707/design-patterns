package by.vsu.kovzov.models.operations;

import by.vsu.kovzov.models.Subscriber;
import by.vsu.kovzov.models.info.CallInfo;
import by.vsu.kovzov.models.tariffs.CallTariff;
import by.vsu.kovzov.models.tariffs.Tariff;

import java.math.BigDecimal;

public class CallOperation extends Operation {
    private CallInfo info;

    public CallOperation(CallInfo info) {
        this.info = info;
    }

    public CallOperation(Long id, CallInfo info) {
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
        if (tariff instanceof CallTariff) {
            return ((CallTariff) tariff).getPrice(info);
        } else {
            throw new RuntimeException("Tariff " + info.getFrom().getTariff() + " doesn't support call operation");
        }
    }

    @Override
    public String toString() {
        return "Call{" +
                "info=" + info +
                '}';
    }
}
