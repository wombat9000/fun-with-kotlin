package iv_conciseness_demo;


import java.util.List;

public class RecommendationMetrics {

    private final RecommendationFetcher recoFetcher;

    public RecommendationMetrics(RecommendationFetcher recoFetcher) {
        this.recoFetcher = recoFetcher;
    }

    public int recoPriceSum(List<Product> products) {

        int sum = 0;

        for (Product product : products) {
            List<Recommendation> recos = recoFetcher.fetchRecosFor(product);

            for (Recommendation reco : recos) {
                sum += reco.getCost();
            }
        }

        return sum;
    }
}
