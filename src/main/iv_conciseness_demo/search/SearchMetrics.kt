package iv_conciseness_demo.search

interface SearchMetrics {

    fun fetchTopTwoResultCounts(searchTerms: List<String>): Map<Produkt, Long>
}