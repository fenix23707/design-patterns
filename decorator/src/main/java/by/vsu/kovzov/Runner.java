package by.vsu.kovzov;

import by.vsu.kovzov.repository.csv.CsvDiscountRepository;
import by.vsu.kovzov.repository.csv.CsvProductMarginRepository;
import by.vsu.kovzov.repository.csv.CsvProductRepository;

public class Runner {

    private static String dir = "src/main/resources/";

    public static void main(String[] args) {
        System.out.println(new CsvProductRepository(dir + "products.csv").getAll());
        System.out.println(new CsvDiscountRepository(dir + "discount.csv").getAll());
        System.out.println(new CsvProductMarginRepository(dir + "margins.csv").getAll());
    }
}
