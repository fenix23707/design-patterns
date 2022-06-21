package by.vsu.kovzov.service.impl;

import by.vsu.kovzov.model.Discount;
import by.vsu.kovzov.repository.DiscountRepository;
import by.vsu.kovzov.service.DiscountService;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private DiscountRepository discountRepository;

    @Override
    public Optional<Discount> getByProductName(String productName) {
        return discountRepository.getAll().stream()
                .filter(discount -> discount.getProductName().equalsIgnoreCase(productName))
                .findAny();
    }
}
