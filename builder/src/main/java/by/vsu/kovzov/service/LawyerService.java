package by.vsu.kovzov.service;

import by.vsu.kovzov.models.EnrichedLawyer;
import by.vsu.kovzov.models.Lawsuit;
import by.vsu.kovzov.models.Lawyer;

import java.util.List;
import java.util.Map;

public interface LawyerService {
    void enrichLawsuits(Lawyer lawyer, List<Lawsuit> lawsuits);

    EnrichedLawyer enrichEfficiency(Lawyer lawyer);
}
