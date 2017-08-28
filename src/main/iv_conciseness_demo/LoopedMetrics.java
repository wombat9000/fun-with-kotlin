package iv_conciseness_demo;

import iv_conciseness_demo.skeletons.Product;
import iv_conciseness_demo.skeletons.Recommendation;
import iv_conciseness_demo.skeletons.RecommendationFetcher;

import java.util.List;

public class LoopedMetrics implements RecommendationMetrics {

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
