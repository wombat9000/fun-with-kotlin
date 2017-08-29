package v_iterating_and_collections.reco;

import v_iterating_and_collections.reco.skeletons.Product;
import v_iterating_and_collections.reco.skeletons.Recommendation;
import v_iterating_and_collections.reco.skeletons.RecommendationFetcher;

import java.util.List;

public class LoopedMetrics implements RecoMetrics {

    private final RecommendationFetcher recoFetcher;

    LoopedMetrics(final RecommendationFetcher recoFetcher) {
        this.recoFetcher = recoFetcher;
    }

    @Override
    public int recoPriceSum(final List<Product> products) {
        int sum = 0;

        for (Product product : products) {
            final List<Recommendation> recos = recoFetcher.fetchRecosFor(product);
            for (Recommendation reco : recos) {
                if(reco.isAvailable()) {
                    sum += reco.getCost();
                }
            }
        }

        return sum;
    }
}
