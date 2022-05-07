package by.vsu.kovzov.utils;

import by.vsu.kovzov.models.Lawsuit;

import java.util.List;

public class LawsuitUtil {
    public static long totalLawsuitDuration(List<Lawsuit> lawsuits) {
        return lawsuits.stream()
                .mapToLong(value ->
                        value.getEnd().getTime() - value.getStart().getTime()
                )
                .sum();
    }

    public static long lawsuitDurationByResult(List<Lawsuit> lawsuits, Lawsuit.Result result) {
        return lawsuits.stream()
                .filter(lawsuit -> lawsuit.getResult().equals(result))
                .mapToLong(value -> value.getEnd().getTime() - value.getStart().getTime())
                .sum();
    }
}
