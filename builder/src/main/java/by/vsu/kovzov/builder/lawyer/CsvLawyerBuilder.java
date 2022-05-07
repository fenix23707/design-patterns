package by.vsu.kovzov.builder.lawyer;

import by.vsu.kovzov.models.Lawyer;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvLawyerBuilder implements LawyerBuilder {
    private static final String DELIMITER = ",";

    private String filePath;

    public CsvLawyerBuilder(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Lawyer> getAllLawyers() {
        List<Lawyer> lawyers = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {

            lawyers = new CsvToBeanBuilder<Lawyer>(reader)
                    .withType(Lawyer.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lawyers;
    }
}
