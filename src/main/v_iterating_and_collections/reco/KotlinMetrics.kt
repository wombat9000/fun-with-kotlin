package v_iterating_and_collections.reco

import v_iterating_and_collections.reco.skeletons.Product
import v_iterating_and_collections.reco.skeletons.RecommendationFetcher

class KotlinMetrics(private val recoFetcher: RecommendationFetcher) : RecoMetrics {
    override fun recoPriceSum(products: List<Product>): Int {
        return products.asSequence()
                .flatMap { recoFetcher.fetchRecosFor(it).asSequence() }
                .filter { it.isAvailable }
                .sumBy { it.cost }
    }
}
