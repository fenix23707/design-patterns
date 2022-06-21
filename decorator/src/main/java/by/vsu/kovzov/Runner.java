package by.vsu.kovzov;

import by.vsu.kovzov.model.Product;
import by.vsu.kovzov.repository.DiscountRepository;
import by.vsu.kovzov.repository.ProductMarginRepository;
import by.vsu.kovzov.repository.ProductRepository;
import by.vsu.kovzov.repository.csv.CsvDiscountRepository;
import by.vsu.kovzov.repository.csv.CsvProductMarginRepository;
import by.vsu.kovzov.repository.csv.CsvProductRepository;
import by.vsu.kovzov.service.DiscountService;
import by.vsu.kovzov.service.ProductMarginService;
import by.vsu.kovzov.service.ProductService;
import by.vsu.kovzov.service.impl.DiscountServiceImpl;
import by.vsu.kovzov.service.impl.ProductMarginServiceImpl;
import by.vsu.kovzov.service.impl.ProductServiceImpl;

import java.util.List;

public class Runner {

    private static String dir = "src/main/resources/";

    public static void main(String[] args) {
        ProductService productService = getProductService();
        List<Product> priceList = productService.getPriceList();

        System.out.println(priceList);
    }

    private static ProductService getProductService() {
        ProductMarginRepository marginRepository = new CsvProductMarginRepository(dir + "margins.csv");
        ProductMarginService marginService = new ProductMarginServiceImpl(marginRepository);

        DiscountRepository discountRepository = new CsvDiscountRepository(dir + "discounts.csv");
        DiscountService discountService = new DiscountServiceImpl(discountRepository);

        ProductRepository productRepository = new CsvProductRepository(dir + "products.csv");

        return new ProductServiceImpl(productRepository, discountService, marginService);
    }
}
