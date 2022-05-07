package by.vsu.kovzov.service;

import by.vsu.kovzov.models.EnrichedLawyer;
import by.vsu.kovzov.models.Lawsuit;
import by.vsu.kovzov.models.Lawyer;
import by.vsu.kovzov.utils.LawsuitUtil;

import java.util.List;
import java.util.stream.Collectors;

import static by.vsu.kovzov.utils.LawyerUtil.mapToEnrichedLawyer;

public class LawyerServiceImpl implements LawyerService {
    @Override
    public void enrichLawsuits(Lawyer lawyer, List<Lawsuit> lawsuits) {
        lawyer.setLawsuits(lawsuits.stream().
                filter(lawsuit -> lawsuit.getLawyer().getId().equals(lawyer.getId()))
                .collect(Collectors.toList())
        );
    }

    @Override
    public EnrichedLawyer enrichEfficiency(Lawyer lawyer) {
        EnrichedLawyer enrichedLawyer = mapToEnrichedLawyer(lawyer);

        long totalTime = LawsuitUtil.totalLawsuitDuration(lawyer.getLawsuits());
        long winTime = LawsuitUtil.lawsuitDurationByResult(lawyer.getLawsuits(), Lawsuit.Result.WIN);
        if (totalTime != 0) {
            enrichedLawyer.setEfficiency(winTime / (double)totalTime);
        }

        return enrichedLawyer;
    }

}
