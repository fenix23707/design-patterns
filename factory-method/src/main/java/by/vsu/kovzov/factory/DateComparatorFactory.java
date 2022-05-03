package by.vsu.kovzov.factory;

import by.vsu.kovzov.comporators.person.DateComparator;
import by.vsu.kovzov.models.Person;

import java.util.Comparator;

public class DateComparatorFactory extends ComparatorFactory {
    public DateComparatorFactory() {
        super("date");
    }

    @Override
    public Comparator<Person> getComparator() {
        return new DateComparator();
    }
}
