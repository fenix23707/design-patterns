package by.vsu.kovzov.repository;

import by.vsu.kovzov.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAll();
}
