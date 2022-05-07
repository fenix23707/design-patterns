package by.vsu.kovzov.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class Lawyer {

    @CsvBindByPosition(position = 0)
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
