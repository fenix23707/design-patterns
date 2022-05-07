package by.vsu.kovzov.builder.lawsuit;

import by.vsu.kovzov.models.Lawsuit;
import by.vsu.kovzov.models.Lawyer;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvBeanIntrospectionException;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvLawsuitBuilder  implements LawsuitBuilder{
    private String filePath;

    public CsvLawsuitBuilder(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Lawsuit> getAllLawsuits() {
        List<Lawsuit> lawsuits = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            lawsuits = new CsvToBeanBuilder<Lawsuit>(reader)
                    .withMappingStrategy(new LawsuitMappingStrategy())
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lawsuits;
    }

    private class LawsuitMappingStrategy extends ColumnPositionMappingStrategy<Lawsuit> {
        public LawsuitMappingStrategy() {
            setType(Lawsuit.class);
        }

        @SneakyThrows
        @Override
        public Lawsuit populateNewBean(String[] line) throws CsvBeanIntrospectionException {
             SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd");

            Lawsuit lawsuit = new Lawsuit();
            lawsuit.setLawyer(new Lawyer());

            lawsuit.setId(Long.parseLong(line[0]));
            lawsuit.getLawyer().setId(Long.parseLong(line[1]));
            lawsuit.setStart(dateFormat.parse(line[2]));
            lawsuit.setEnd(dateFormat.parse(line[3]));
            lawsuit.setDescription(line[4]);
            lawsuit.setResult(Lawsuit.Result.valueOf(line[5]));

            return lawsuit;
        }
    }
}
