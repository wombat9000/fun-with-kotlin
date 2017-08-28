package iv_conciseness_demo;


import java.util.List;

public class LoopedMetrics implements RecommendationMetrics {

    private final RecommendationFetcher recoFetcher;

    LoopedMetrics(RecommendationFetcher recoFetcher) {
        this.recoFetcher = recoFetcher;
    }

    @Override
    public int recoPriceSum(List<Product> products) {

        int sum = 0;

        for (Product product : products) {
            List<Recommendation> recos = recoFetcher.fetchRecosFor(product);
            for (Recommendation reco : recos) {
                if(reco.isAvailable()) {
                    sum += reco.getCost();
                }
            }
        }

        return sum;
    }
}
