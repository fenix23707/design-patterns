package by.vsu.kovzov.factory;

import by.vsu.kovzov.models.Person;

import java.util.Comparator;

public abstract class ComparatorFactory<T> {
    private String name;

    public ComparatorFactory(String name) {
        this.name = name;
    }

    public abstract Comparator<T> getComparator();

    @Override
    public String toString() {
        return "by " + name;
    }
}
