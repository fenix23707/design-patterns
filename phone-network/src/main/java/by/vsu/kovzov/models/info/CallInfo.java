package by.vsu.kovzov.models.info;

import by.vsu.kovzov.models.Subscriber;

import java.util.Date;

public class CallInfo {
    private Subscriber from;

    private Subscriber to;

    private Date start;

    private Date end;

    public CallInfo(Subscriber from, Subscriber to, Date start, Date end) {
        this.from = from;
        this.to = to;
        this.start = start;
        this.end = end;
    }

    public Subscriber getFrom() {
        return from;
    }

    public Subscriber getTo() {
        return to;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
