package by.vsu.kovzov.factory;

import by.vsu.kovzov.comporators.person.WeekDayComparator;
import by.vsu.kovzov.models.Person;

import java.util.Comparator;

public class WeekDayComparatorFactory extends ComparatorFactory<Person> {
    public WeekDayComparatorFactory() {
        super("week day");
    }

    @Override
    public Comparator<Person> getComparator() {
        return new WeekDayComparator();
    }
}
