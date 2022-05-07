package by.vsu.kovzov;

import by.vsu.kovzov.builder.lawsuit.CsvLawsuitBuilder;
import by.vsu.kovzov.builder.lawyer.CsvLawyerBuilder;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(new CsvLawyerBuilder("data/lawyer/lawyers.csv").getAllLawyers());
        System.out.println(new CsvLawsuitBuilder("data/lawsuit/lawsuits.csv").getAllLawsuits());
    }
}
