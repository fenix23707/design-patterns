package by.vsu.kovzov.comparators;

import by.vsu.kovzov.models.EnrichedLawyer;

import java.util.Comparator;

public class SpecializationLawyerComparator implements Comparator<EnrichedLawyer> {
    @Override
    public int compare(EnrichedLawyer o1, EnrichedLawyer o2) {
        String spec1 = o1.getSpecialty();
        String spec2 = o2.getSpecialty();

        int res = spec1.compareTo(spec2);
        if (res == 0) {
            return Double.compare(o1.getEfficiency(), o2.getEfficiency());
        }
        return res;
    }
}
