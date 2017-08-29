package v_iterating_and_collections.reco;

import v_iterating_and_collections.reco.skeletons.Product;
import v_iterating_and_collections.reco.skeletons.Recommendation;
import v_iterating_and_collections.reco.skeletons.RecommendationFetcher;

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
