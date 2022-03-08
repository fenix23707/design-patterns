package by.vsu.models;

import java.io.Serializable;
import java.util.Objects;

public class Phone implements Serializable {
    private String countryCode;

    private String operatorCode;

    private String phoneNumber;

    public Phone() {
    }

    public Phone(String countryCode, String operatorCode, String phoneNumber) {
        setCountryCode(countryCode);
        setOperatorCode(operatorCode);
        setPhoneNumber(phoneNumber);
    }

    public void setCountryCode(String countryCode) {
        checkIsCountryCode(countryCode);
        this.countryCode = countryCode;
    }

    public void setOperatorCode(String operatorCode) {
        checkIsOperatorCode(operatorCode);
        this.operatorCode = operatorCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        checkIsPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private void checkIsCountryCode(String str) {
        if (str.length() != 5 || !isNumber(str)) {
            throw new RuntimeException(str + " is not country code");
        }
    }

    private void checkIsOperatorCode(String str) {
        if (str.length() != 2 || !isNumber(str)) {
            throw new RuntimeException(str + " is not operator code");
        }
    }

    private void checkIsPhoneNumber(String str) {
        if (str.length() != 7 || !isNumber(str)) {
            throw new RuntimeException(str + " is not phone number");
        }
    }

    private boolean isNumber(String str) {
        return str.matches("\\w+");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(countryCode, phone.countryCode) && Objects.equals(operatorCode, phone.operatorCode) && Objects.equals(phoneNumber, phone.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, operatorCode, phoneNumber);
    }

    @Override
    public String toString() {
        return countryCode + "-" + operatorCode + "-" + phoneNumber;
    }
}
