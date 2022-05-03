package by.vsu.kovzov.factory;

import by.vsu.kovzov.comporators.person.CreditCardComparator;
import by.vsu.kovzov.models.Person;

import java.util.Comparator;

public class CreditCardComparatorFactory extends ComparatorFactory<Person> {
    public CreditCardComparatorFactory() {
        super("credit card");
    }

    @Override
    public Comparator<Person> getComparator() {
        return new CreditCardComparator();
    }
}
