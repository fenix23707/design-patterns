package by.vsu.kovzov;

import by.vsu.kovzov.repository.csv.CsvProductRepository;

public class Runner {
    public static void main(String[] args) {
        System.out.println(new CsvProductRepository("src/main/resources/products.csv").getAll());
    }
}
