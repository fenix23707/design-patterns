package by.vsu.kovzov.model;

import lombok.Data;

import java.util.Date;

@Data
public class Lawsuit {
    private Lawyer lawyer;
    private Date start;
    private Date end;
    private String description;
    private Result result;


    public enum Result {
        WIN, LOSE
    }

}
