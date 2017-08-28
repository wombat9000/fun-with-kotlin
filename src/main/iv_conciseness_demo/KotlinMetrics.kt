package iv_conciseness_demo

import iv_conciseness_demo.skeletons.Product
import iv_conciseness_demo.skeletons.RecommendationFetcher

class KotlinMetrics(private val recoFetcher: RecommendationFetcher) : RecommendationMetrics {
    override fun recoPriceSum(products: List<Product>): Int {
        return products
                .flatMap { recoFetcher.fetchRecosFor(it) }
                .filter { it.isAvailable }
                .map { it.cost }
                .sum()
    }
}
