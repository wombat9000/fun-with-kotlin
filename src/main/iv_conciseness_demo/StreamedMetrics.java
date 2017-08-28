package iv_conciseness_demo;

import iv_conciseness_demo.skeletons.Product;
import iv_conciseness_demo.skeletons.Recommendation;
import iv_conciseness_demo.skeletons.RecommendationFetcher;

import java.util.List;

public class StreamedMetrics implements RecommendationMetrics {

    private final RecommendationFetcher recoFetcher;

    StreamedMetrics(final RecommendationFetcher recoFetcher) {
        this.recoFetcher = recoFetcher;
    }

    @Override
    public int recoPriceSum(final List<Product> products) {
        return products.stream()
                .map(recoFetcher::fetchRecosFor)
                .flatMap(List::stream)
                .filter(Recommendation::isAvailable)
                .map(Recommendation::getCost)
                .reduce(0, Integer::sum);
    }
}
