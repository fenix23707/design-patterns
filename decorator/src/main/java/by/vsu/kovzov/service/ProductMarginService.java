package by.vsu.kovzov.service;

import by.vsu.kovzov.model.ProductMargin;

public interface ProductMarginService {

    ProductMargin getByCategoryOrDefault(String category);
}
