package by.vsu.kovzov.service;

import by.vsu.kovzov.model.Discount;

import java.util.Optional;

public interface DiscountService {

    Optional<Discount> getByProductName(String productName);
}
