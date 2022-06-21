package by.vsu.kovzov.repository;

import by.vsu.kovzov.model.Discount;

import java.util.List;

public interface DiscountRepository {
    List<Discount> getAll();
}
