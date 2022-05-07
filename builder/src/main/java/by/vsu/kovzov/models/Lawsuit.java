package by.vsu.kovzov.models;

import lombok.Data;

import java.util.Date;

@Data
public class Lawsuit {
    private Long id;
    private Lawyer lawyer;
    private Date start;
    private Date end;
    private String description;
    private Result result;


    public enum Result {
        WIN, LOSE
    }

    @Override
    public String toString() {
        return "Lawsuit{" +
                "id=" + id +
                ", lawyer=" + lawyer.getId() +
                ", start=" + start +
                ", end=" + end +
                ", description='" + description + '\'' +
                ", result=" + result +
                '}';
    }
}
