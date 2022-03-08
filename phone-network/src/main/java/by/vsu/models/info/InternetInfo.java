package by.vsu.models.info;

import by.vsu.models.Subscriber;

import java.io.Serializable;
import java.util.Date;

public class InternetInfo implements Serializable {
    private Subscriber owner;

    private long traffic;

    private Date start;

    private Date end;

    public InternetInfo() {
    }

    public InternetInfo(Subscriber owner, long traffic, Date start, Date end) {
        this.owner = owner;
        this.traffic = traffic;
        this.start = start;
        this.end = end;
    }

    public Subscriber getOwner() {
        return owner;
    }

    public void setOwner(Subscriber owner) {
        this.owner = owner;
    }

    public long getTraffic() {
        return traffic;
    }

    public void setTraffic(long traffic) {
        this.traffic = traffic;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" +
                "owner=" + owner +
                ", traffic=" + traffic +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
