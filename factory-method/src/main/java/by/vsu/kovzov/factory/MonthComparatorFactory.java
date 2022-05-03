package by.vsu.kovzov.factory;

import by.vsu.kovzov.comporators.person.MonthComparator;
import by.vsu.kovzov.models.Person;

import java.util.Comparator;

public class MonthComparatorFactory extends ComparatorFactory<Person> {
    public MonthComparatorFactory() {
        super("month");
    }

    @Override
    public Comparator<Person> getComparator() {
        return new MonthComparator();
    }
}
