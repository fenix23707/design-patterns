package by.vsu.kovzov.repository.csv;

import by.vsu.kovzov.model.Discount;
import by.vsu.kovzov.repository.DiscountRepository;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvBeanIntrospectionException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.util.List;

@AllArgsConstructor
public class CsvDiscountRepository implements DiscountRepository {

    private String filePath;

    @Override
    @SneakyThrows
    public List<Discount> getAll() {
        List<Discount> discounts;
        try (FileReader reader = new FileReader(filePath)) {
            discounts = new CsvToBeanBuilder<Discount>(reader)
                    .withSeparator(';')
                    .withMappingStrategy(new DiscountMappingStrategy())
                    .build()
                    .parse();
        }
        return discounts;
    }

    private class DiscountMappingStrategy extends ColumnPositionMappingStrategy<Discount> {
        public DiscountMappingStrategy() {
            setType(Discount.class);
        }

        @SneakyThrows
        @Override
        public Discount populateNewBean(String[] line) throws CsvBeanIntrospectionException {
            Discount discount = new Discount();

            discount.setProductName(line[0]);
            discount.setType(Discount.Type.valueOf(line[1]));
            discount.setValue(Double.parseDouble(line[2]));

            return discount;
        }
    }
}
