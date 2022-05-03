package by.vsu.kovzov;

import by.vsu.kovzov.model.Person;

import java.util.Calendar;
import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    private String type;

    public PersonComparator(String type) {
        this.type = type;
    }

    @Override
    public int compare(Person p1, Person p2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(p1.getBirthday());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(p2.getBirthday());
        switch(type) {
            case "by date": return c1.compareTo(c2);
            case "by day of week": return Integer.compare(c1.get(Calendar.DAY_OF_WEEK), c2.get(Calendar.DAY_OF_WEEK));
            case "by month": return Integer.compare(c1.get(Calendar.MONTH), c2.get(Calendar.MONTH));
        }
        throw new IllegalArgumentException();
    }
}
