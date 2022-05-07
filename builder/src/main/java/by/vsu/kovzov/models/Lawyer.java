package by.vsu.kovzov.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "lawyer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lawyer {

    @CsvBindByPosition(position = 0)
    @XmlAttribute(name = "id")
    private Long id;

    @CsvBindByPosition(position = 1)
    private String surname;

    @CsvBindByPosition(position = 2)
    private String name;

    @CsvBindByPosition(position = 3)
    private String patronymic;

    @CsvBindByPosition(position = 4)
    private String specialty;
}
