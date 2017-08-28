package iv_conciseness_demo.reco

import iv_conciseness_demo.reco.skeletons.Product
import iv_conciseness_demo.reco.skeletons.RecommendationFetcher

class KotlinMetrics(private val recoFetcher: RecommendationFetcher) : RecoMetrics {
    override fun recoPriceSum(products: List<Product>): Int {
        return products.asSequence()
                .flatMap { recoFetcher.fetchRecosFor(it).asSequence() }
                .filter { it.isAvailable }
                .map { it.cost }
                .sum()
    }
}
