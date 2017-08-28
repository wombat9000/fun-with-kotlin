package iv_conciseness_demo

class KotlinMetrics internal constructor(private val recoFetcher: RecommendationFetcher) : RecommendationMetrics {

    override fun recoPriceSum(products: List<Product>): Int {
        return products
                .flatMap { recoFetcher.fetchRecosFor(it) }
                .filter { it.isAvailable }
                .map { it.cost }
                .sum()
    }
}
