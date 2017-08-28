package iv_conciseness_demo;

import iv_conciseness_demo.skeletons.Product;

import java.util.List;

public interface RecommendationMetrics {
    int recoPriceSum(List<Product> products);
}
