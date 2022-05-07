package by.vsu.kovzov.models;

import lombok.Data;

@Data
public class EnrichedLawyer extends Lawyer {
    private double efficiency;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        getLawsuits().forEach(lawsuit -> builder.append('\t').append(lawsuit).append("\n"));

        return String.format("%d\t%s\t%s\t%s\t%s\t%f:\n%s ",
                getId(),
                getName(),
                getSurname(),
                getPatronymic(),
                getSpecialty(),
                getEfficiency(),
                builder);
    }
}
