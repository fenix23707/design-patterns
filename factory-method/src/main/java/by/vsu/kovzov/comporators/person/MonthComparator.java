package by.vsu.kovzov.comporators.person;

import by.vsu.kovzov.models.Person;

import java.util.Calendar;

public class MonthComparator implements PersonComparator{
    @Override
    public int compare(Person p1, Person p2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(p1.getBirthday());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(p2.getBirthday());

        return Integer.compare(c1.get(Calendar.MONTH), c2.get(Calendar.MONTH));
    }
}
