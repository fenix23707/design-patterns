package by.vsu.kovzov.comporators.person;

import by.vsu.kovzov.models.Person;

import java.util.Comparator;

public interface PersonComparator extends Comparator<Person> {
    @Override
    int compare(Person p1, Person p2);
}
