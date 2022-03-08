package by.vsu.kovzov.models.info;

import by.vsu.kovzov.models.Subscriber;

import java.io.Serializable;
import java.util.Date;

public class CallInfo implements Serializable {
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

    @Override
    public String toString() {
        return "{" +
                "from=" + from +
                ", to=" + to +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
