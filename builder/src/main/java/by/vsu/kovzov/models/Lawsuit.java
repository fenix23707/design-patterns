package by.vsu.kovzov.models;

import lombok.Data;

import javax.swing.text.DateFormatter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@XmlRootElement(name = "lawsuit")
@XmlAccessorType(XmlAccessType.FIELD)
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return "Lawsuit{" +
                "id=" + id +
                ", lawyer=" + lawyer.getId() +
                ", start=" + format.format(start) +
                ", end=" + format.format(end) +
                ", description='" + description + '\'' +
                ", result=" + result +
                '}';
    }
}
