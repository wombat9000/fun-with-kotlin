package v_iterating_and_collections.search

interface SearchMetrics {

    fun fetchTopTwoResultCounts(searchTerms: List<String>): Map<Produkt, Int>
}