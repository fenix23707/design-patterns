package by.vsu.kovzov.repository.csv;

import by.vsu.kovzov.model.Product;
import by.vsu.kovzov.repository.ProductRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.util.List;

@AllArgsConstructor
public class CsvProductRepository implements ProductRepository {

    private String filePath;

    @Override
    @SneakyThrows
    public List<Product> getAll() {
        List<Product> products;
        try (FileReader reader = new FileReader(filePath)){
            products = new CsvToBeanBuilder<Product>(reader)
                    .withType(Product.class)
                    .withSeparator(';')
                    .build()
                    .parse();
        }
        return products;
    }


}
