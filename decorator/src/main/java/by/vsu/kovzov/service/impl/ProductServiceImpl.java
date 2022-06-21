package by.vsu.kovzov.service.impl;

import by.vsu.kovzov.model.Product;
import by.vsu.kovzov.repository.ProductRepository;
import by.vsu.kovzov.service.DiscountService;
import by.vsu.kovzov.service.ProductMarginService;
import by.vsu.kovzov.service.ProductService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private DiscountService discountService;

    private ProductMarginService productMarginService;

    @Override
    public List<Product> getPriceList() {
        return productRepository.getAll();
    }
}
