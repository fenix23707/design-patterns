package by.vsu.kovzov.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Subscriber implements Serializable {
    private Phone phone;

    private BigDecimal balance = BigDecimal.ZERO;

    private TariffPlan tariffPlan;

    public Subscriber() {
    }

    public Subscriber(Phone phone, TariffPlan tariffPlan) {
        this(phone, BigDecimal.ZERO, tariffPlan);
    }

    public Subscriber(Phone phone, BigDecimal balance, TariffPlan tariffPlan) {
        setPhone(phone);
        setBalance(balance);
        setTariffPlan(tariffPlan);
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
        checkIsBalance(balance);
        this.balance = balance;
    }

    public TariffPlan getTariffPlan() {
        return tariffPlan;
    }

    public void setTariffPlan(TariffPlan tariffPlan) {
        this.tariffPlan = tariffPlan;
    }

    private void checkIsBalance(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Balance = " + balance + " can't be lower than zero.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return Objects.equals(phone, that.phone) && Objects.equals(balance, that.balance) && tariffPlan == that.tariffPlan;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, balance, tariffPlan);
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "phone=" + phone +
                ", balance=" + balance +
                ", tariffPlan=" + tariffPlan +
                '}';
    }
}
