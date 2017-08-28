package iv_conciseness_demo.search

interface SearchMetrics {

    fun fetchResultCounts(searchTerms: List<String>): Map<Produkt, Int>
}