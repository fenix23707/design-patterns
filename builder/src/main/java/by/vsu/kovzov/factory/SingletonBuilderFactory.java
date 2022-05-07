package by.vsu.kovzov.factory;

import by.vsu.kovzov.builder.lawsuit.CsvLawsuitBuilder;
import by.vsu.kovzov.builder.lawsuit.LawsuitBuilder;
import by.vsu.kovzov.builder.lawsuit.XmlLawsuitBuilder;
import by.vsu.kovzov.builder.lawyer.CsvLawyerBuilder;
import by.vsu.kovzov.builder.lawyer.LawyerBuilder;
import by.vsu.kovzov.builder.lawyer.XmlLawyerBuilder;
import org.apache.commons.io.FilenameUtils;

import static java.lang.String.format;

public class SingletonBuilderFactory implements BuilderFactory {

    private final static SingletonBuilderFactory INSTANCE = new SingletonBuilderFactory();

    private SingletonBuilderFactory() {
    }

    public static SingletonBuilderFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public LawyerBuilder getLawyerBuilder(String filePath) {
        String extension = FilenameUtils.getExtension(filePath);
        switch (extension) {
            case "csv": return new CsvLawyerBuilder(filePath);
            case "xml": return new XmlLawyerBuilder(filePath);
            default:
                throw new RuntimeException(format("extension %s is unsupported", extension));
        }
    }

    @Override
    public LawsuitBuilder getLawsuitBuilder(String filePath) {
        String extension = FilenameUtils.getExtension(filePath);
        switch (extension) {
            case "csv": return new CsvLawsuitBuilder(filePath);
            case "xml": return new XmlLawsuitBuilder(filePath);
            default:
                throw new RuntimeException(format("extension %s is unsupported", extension));
        }
    }
}
