package by.vsu.models.info;

import by.vsu.models.Subscriber;

import java.io.Serializable;

public class SmsInfo implements Serializable {
    private Subscriber from;

    private Subscriber to;

    public SmsInfo() {
    }

    public SmsInfo(Subscriber from, Subscriber to) {
        this.from = from;
        this.to = to;
    }

    public Subscriber getFrom() {
        return from;
    }

    public void setFrom(Subscriber from) {
        this.from = from;
    }

    public Subscriber getTo() {
        return to;
    }

    public void setTo(Subscriber to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
