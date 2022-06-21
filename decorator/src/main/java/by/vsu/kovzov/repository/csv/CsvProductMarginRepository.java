package by.vsu.kovzov.repository.csv;

import by.vsu.kovzov.model.ProductMargin;
import by.vsu.kovzov.repository.ProductMarginRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.util.List;

@AllArgsConstructor
public class CsvProductMarginRepository implements ProductMarginRepository {
    private String filePath;

    @Override
    @SneakyThrows
    public List<ProductMargin> getAll() {
        List<ProductMargin> productMargins;
        try (FileReader reader = new FileReader(filePath)){
            productMargins = new CsvToBeanBuilder<ProductMargin>(reader)
                    .withType(ProductMargin.class)
                    .withSeparator(';')
                    .build()
                    .parse();
        }
        return productMargins;
    }
}
