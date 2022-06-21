package by.vsu.kovzov.service.impl;

import by.vsu.kovzov.decorators.DiscountDecorator;
import by.vsu.kovzov.decorators.MarginDecorator;
import by.vsu.kovzov.model.Discount;
import by.vsu.kovzov.model.ProductMargin;
import by.vsu.kovzov.model.Tradable;
import by.vsu.kovzov.repository.ProductRepository;
import by.vsu.kovzov.service.DiscountService;
import by.vsu.kovzov.service.ProductMarginService;
import by.vsu.kovzov.service.ProductService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private DiscountService discountService;

    private ProductMarginService productMarginService;

    @Override
    public List<Tradable> getPriceList() {
        return productRepository.getAll().stream()
                .map(product -> (Tradable) product)
                .map(this::marginDecorate)
                .map(this::discountDecorate)
                .collect(Collectors.toList());
    }

    private Tradable marginDecorate(Tradable tradable) {
        ProductMargin margin = productMarginService.getByCategoryOrDefault(tradable.getCategory());

        return new MarginDecorator(tradable, margin.getPercent());
    }

    private Tradable discountDecorate(Tradable tradable) {
        Optional<Discount> discount = discountService.getByProductName(tradable.getName());
        if (discount.isEmpty()) {
            return tradable;
        }

        return new DiscountDecorator(tradable, discount.get());
    }
}
