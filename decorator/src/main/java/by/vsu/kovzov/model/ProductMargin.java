package by.vsu.kovzov.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class ProductMargin {

    @CsvBindByPosition(position = 0)
    String category;

    @CsvBindByPosition(position = 1)
    double percent;
}
