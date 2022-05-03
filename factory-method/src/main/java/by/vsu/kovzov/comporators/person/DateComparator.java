package by.vsu.kovzov.comporators.person;

import by.vsu.kovzov.models.Person;

import java.util.Calendar;
import java.util.Comparator;

public class DateComparator implements PersonComparator {
    @Override
    public int compare(Person p1, Person p2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(p1.getBirthday());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(p2.getBirthday());
        return c1.compareTo(c2);
    }
}
