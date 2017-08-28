package iv_conciseness_demo;

import java.util.List;

public class StreamedMetrics implements RecommendationMetrics {

    private final RecommendationFetcher recoFetcher;

    StreamedMetrics(RecommendationFetcher recoFetcher) {
        this.recoFetcher = recoFetcher;
    }

    @Override
    public int recoPriceSum(List<Product> products) {
        return products.stream()
                .map(recoFetcher::fetchRecosFor)
                .flatMap(List::stream)
                .filter(Recommendation::isAvailable)
                .map(Recommendation::getCost)
                .reduce(0, Integer::sum);
    }
}
