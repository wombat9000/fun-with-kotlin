package iv_conciseness_demo.reco;

import iv_conciseness_demo.reco.skeletons.Product;
import iv_conciseness_demo.reco.skeletons.Recommendation;
import iv_conciseness_demo.reco.skeletons.RecommendationFetcher;

import java.util.List;

public class StreamedMetrics implements RecoMetrics {

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
