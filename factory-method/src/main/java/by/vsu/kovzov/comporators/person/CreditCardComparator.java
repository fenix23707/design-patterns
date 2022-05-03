package by.vsu.kovzov.comporators.person;

import by.vsu.kovzov.models.Person;

public class CreditCardComparator implements PersonComparator{
    @Override
    public int compare(Person p1, Person p2) {

        return Integer.compare(getLastGroup(p1.getCreditCardNumber()), getLastGroup(p2.getCreditCardNumber()));
    }

    private int getLastGroup(String creditCardNum) {
        return Integer.parseInt(creditCardNum.split(" ")[3]);
    }
}
