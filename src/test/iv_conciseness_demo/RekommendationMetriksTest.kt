package iv_conciseness_demo

import iv_conciseness_demo.skeletons.Product
import iv_conciseness_demo.skeletons.Recommendation
import iv_conciseness_demo.skeletons.RecommendationFetcher
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import org.testng.annotations.DataProvider
import org.testng.annotations.Test


class RekommendationMetriksTest {

    @Mock
    lateinit var recoFetcher: RecommendationFetcher

    private fun loopsTestee(): RecommendationMetrics {
        return LoopedMetrics(recoFetcher)
    }

    private fun streamsTestee(): RecommendationMetrics {
        return StreamedMetrics(recoFetcher)
    }

    private fun kotlinTestee(): RecommendationMetrics {
        return KotlinMetrics(recoFetcher)
    }

    @DataProvider(name = "testSubjects")
    fun testSubjects(): Array<Array<Any>> {
        initMocks(this)

        return arrayOf(
                arrayOf<Any>(loopsTestee()),
                arrayOf<Any>(streamsTestee()),
                arrayOf<Any>(kotlinTestee()))
    }

    @Test(dataProvider = "testSubjects")
    fun shouldHandleEmptyInput(testee: RecommendationMetrics) {
        val zeroProducts = listOf<Product>()

        val result = testee.recoPriceSum(zeroProducts)

        assertThat(result, `is`(0))
    }

    @Test(dataProvider = "testSubjects")
    fun shouldWorkForSingleProduct(testee: RecommendationMetrics) {
        val product = Product()
        val singleProduct = listOf(product)

        val recoWithCost10 = Recommendation(1, 10, false)
        val recommenations = listOf(recoWithCost10)

        given(recoFetcher.fetchRecosFor(product)).willReturn(recommenations)

        val result = testee.recoPriceSum(singleProduct)
        assertThat(result, `is`(10))
    }

    @Test(dataProvider = "testSubjects")
    fun shouldWorkForMultipleProducts(testee: RecommendationMetrics) {
        val product = Product()
        val anotherProduct = Product()
        val twoProducts = listOf(product, anotherProduct)

        val recoWithCost10 = Recommendation(1, 10, false)
        val firstSetOfRecos = listOf(recoWithCost10)

        val recoWithCost20 = Recommendation(2, 20, false)
        val secondSetOfRecos = listOf(recoWithCost20)

        given(recoFetcher.fetchRecosFor(product)).willReturn(firstSetOfRecos)
        given(recoFetcher.fetchRecosFor(anotherProduct)).willReturn(secondSetOfRecos)

        val result = testee.recoPriceSum(twoProducts)
        assertThat(result, `is`(30))
    }

    @Test(dataProvider = "testSubjects")
    fun shouldWorkForMultipleRecosForSameProduct(testee: RecommendationMetrics) {
        val product = Product()
        val twoProducts = listOf(product)

        val recoWithCost10 = Recommendation(1, 10, false)
        val recoWithCost20 = Recommendation(2, 20, false)
        val firstSetOfRecos = listOf(recoWithCost10, recoWithCost20)

        given(recoFetcher.fetchRecosFor(product)).willReturn(firstSetOfRecos)

        val result = testee.recoPriceSum(twoProducts)
        assertThat(result, `is`(30))
    }

    @Test(dataProvider = "testSubjects")
    fun shouldNotConsiderSoldoutRecos(testee: RecommendationMetrics) {
        val product = Product()
        val twoProducts = listOf(product)

        val recoWithCost10 = Recommendation(1, 10, false)
        val recoWithCost20 = Recommendation(2, 20, true)
        val firstSetOfRecos = listOf(recoWithCost10, recoWithCost20)

        given(recoFetcher.fetchRecosFor(product)).willReturn(firstSetOfRecos)

        val result = testee.recoPriceSum(twoProducts)
        assertThat(result, `is`(10))
    }
}