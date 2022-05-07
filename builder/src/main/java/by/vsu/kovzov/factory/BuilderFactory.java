package by.vsu.kovzov.factory;

import by.vsu.kovzov.builder.lawsuit.LawsuitBuilder;
import by.vsu.kovzov.builder.lawyer.LawyerBuilder;

public interface BuilderFactory {
    LawyerBuilder getLawyerBuilder(String filePath);

    LawsuitBuilder getLawsuitBuilder(String filePath);
}
