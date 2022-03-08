package by.vsu.kovzov.models;

import by.vsu.kovzov.models.tariffs.Tariff;

import java.io.Serializable;
import java.math.BigDecimal;

public class Subscriber implements Serializable {
    private Phone phone;

    private BigDecimal balance = BigDecimal.ZERO;

    private Tariff tariff;

    public Subscriber() {
    }

    public Subscriber(Phone phone, Tariff tariff) {
        this.phone = phone;
        this.tariff = tariff;
    }

    public Subscriber(Phone phone, BigDecimal balance, Tariff tariff) {
        this.phone = phone;
        this.balance = balance;
        this.tariff = tariff;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        return "{" +
                "phone=" + phone +
                ", balance=" + balance +
                ", tariff=" + tariff +
                '}';
    }
}
