package by.vsu.kovzov.service.impl;

import by.vsu.kovzov.model.ProductMargin;
import by.vsu.kovzov.repository.ProductMarginRepository;
import by.vsu.kovzov.service.ProductMarginService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProductMarginServiceImpl implements ProductMarginService {

    private static final String DEFAULT_CATEGORY = "default";

    private final ProductMarginRepository productMarginRepository;

    @Override
    public ProductMargin getByCategoryOrDefault(String category) {
        List<ProductMargin> margins = productMarginRepository.getAll();
        ProductMargin productMargin = margins.stream()
                .filter(pm -> pm.getCategory().equalsIgnoreCase(category))
                .findAny().orElse(null);

        if (productMargin == null) {
            productMargin = margins.stream()
                    .filter(pm -> pm.getCategory().equalsIgnoreCase(DEFAULT_CATEGORY))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("can't find default margin"));
        }

        return productMargin;
    }
}
