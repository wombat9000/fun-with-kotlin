package iv_conciseness_demo.search

class KotlinMetrics(private val searchEngine: SearchEngine) : SearchMetrics {
    override fun fetchTopTwoResultCounts(searchTerms: List<String>): Map<Produkt, Int> {
        return searchTerms
                .mapNotNull { searchEngine.searchFor(it) }
                .flatMap { it.take(2) }
                .groupingBy { it }
                .eachCount()
    }
}

