package by.vsu.kovzov.models;

import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Person {
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthday;
    private String creditCardNumber;

    public Person() {
        Random random = new Random();
        this.creditCardNumber = IntStream.range(0, 4)
                .mapToObj(value -> String.format("%04d", random.nextInt(1001)))
                .collect(Collectors.joining(" "));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}