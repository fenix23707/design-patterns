package by.vsu.kovzov.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class Product implements Tradable {

    @CsvBindByPosition(position = 0)
    String category;

    @CsvBindByPosition(position = 1)
    String name;

    @CsvBindByPosition(position = 2)
    double price;
}
