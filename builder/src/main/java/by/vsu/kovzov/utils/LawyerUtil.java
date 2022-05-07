package by.vsu.kovzov.utils;

import by.vsu.kovzov.models.EnrichedLawyer;
import by.vsu.kovzov.models.Lawyer;

public class LawyerUtil {
    public static EnrichedLawyer mapToEnrichedLawyer(Lawyer lawyer) {
        EnrichedLawyer enrichedLawyer = new EnrichedLawyer();
        enrichedLawyer.setId(lawyer.getId());
        enrichedLawyer.setLawsuits(lawyer.getLawsuits());
        enrichedLawyer.setName(lawyer.getName());
        enrichedLawyer.setSurname(lawyer.getSurname());
        enrichedLawyer.setPatronymic(lawyer.getPatronymic());
        enrichedLawyer.setSpecialty(lawyer.getSpecialty());
        return enrichedLawyer;
    }
}
