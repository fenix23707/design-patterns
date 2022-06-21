package by.vsu.kovzov;

import by.vsu.kovzov.model.Tradable;
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

import static java.lang.String.format;

public class Runner {

    private static String dir = "src/main/resources/";

    public static void main(String[] args) {
        ProductService productService = getProductService();
        List<Tradable> priceList = productService.getPriceList();

       printPriceList(priceList);
    }

    private static void printPriceList(List<Tradable> priceList) {
        priceList.forEach(product -> {
            System.out.println(format("%-30s%-30s\t%f", product.getCategory(), product.getName(), product.getPrice()));
        });
    }

    private static ProductService getProductService() {
        ProductMarginRepository marginRepository = new CsvProductMarginRepository(dir + "margins.csv");
        ProductMarginService marginService = new ProductMarginServiceImpl(marginRepository);

        DiscountRepository discountRepository = new CsvDiscountRepository(dir + "discount.csv");
        DiscountService discountService = new DiscountServiceImpl(discountRepository);

        ProductRepository productRepository = new CsvProductRepository(dir + "products.csv");

        return new ProductServiceImpl(productRepository, discountService, marginService);
    }
}
