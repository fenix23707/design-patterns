package by.vsu.kovzov.comparators;

import by.vsu.kovzov.models.Lawsuit;

import java.util.Comparator;
import java.util.Date;

public class StartDateLawsuitComparator implements Comparator<Lawsuit> {
    @Override
    public int compare(Lawsuit o1, Lawsuit o2) {
        Date s1 = o1.getStart();
        Date s2 = o2.getStart();
        return s1.compareTo(s2);
    }
}
