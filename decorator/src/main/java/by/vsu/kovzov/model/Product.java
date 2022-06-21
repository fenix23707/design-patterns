package by.vsu.kovzov.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    @CsvBindByPosition(position = 0)
    String category;
    @CsvBindByPosition(position = 1)
    String name;
    @CsvBindByPosition(position = 2)
    double price;
}
