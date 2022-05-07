package by.vsu.kovzov.controlles;

import by.vsu.kovzov.builder.lawsuit.LawsuitBuilder;
import by.vsu.kovzov.builder.lawyer.LawyerBuilder;
import by.vsu.kovzov.factory.BuilderFactory;
import by.vsu.kovzov.models.EnrichedLawyer;
import by.vsu.kovzov.models.Lawsuit;
import by.vsu.kovzov.models.Lawyer;
import by.vsu.kovzov.service.LawyerService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LawController {
    private BuilderFactory builderFactory;

    private LawyerService lawyerService;

    private List<Lawyer> lawyers;

    private List<EnrichedLawyer> enrichedLawyers;

    public LawController(BuilderFactory builderFactory, LawyerService lawyerService) {
        this.builderFactory = builderFactory;
        this.lawyerService = lawyerService;
    }

    public List<EnrichedLawyer> getResult() {
        return enrichedLawyers;
    }

    public void loadLawyers(String filePath) {
        LawyerBuilder lawyerBuilder = builderFactory.getLawyerBuilder(filePath);
        lawyers = lawyerBuilder.getAllLawyers();
    }

    public void enrichWithLawsuits(String filePath) {
        LawsuitBuilder lawsuitBuilder = builderFactory.getLawsuitBuilder(filePath);
        List<Lawsuit> lawsuits = lawsuitBuilder.getAllLawsuits();

        lawyers.forEach(lawyer -> lawyerService.enrichLawsuits(lawyer, lawsuits));
    }

    public void sortLawsuits(Comparator<Lawsuit> comparator) {
        lawyers.forEach(lawyer -> Collections.sort(lawyer.getLawsuits(), comparator));
    }

    public void enrichEfficiency() {
        enrichedLawyers = lawyers.stream()
                .map(lawyer -> lawyerService.enrichEfficiency(lawyer))
                .collect(Collectors.toList());
    }

    public void sortEnrichedLawyers(Comparator<EnrichedLawyer> comparator) {
        Collections.sort(enrichedLawyers, comparator);
    }


}
