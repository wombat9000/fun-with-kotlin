package iv_conciseness_demo;

import org.mockito.Mock;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class RecommendationMetricsTest {

    @Mock
    RecommendationFetcher recoFetcher;

    private RecommendationMetrics loopsTestee() {
        initMocks(this);
        return new RecommendationMetrics(recoFetcher);
    }

    @DataProvider(name = "testSubjects")
    public Object[][] testSubjects() {
        return new Object[][] {
                { loopsTestee() },
        };

    }

    @Test(dataProvider = "testSubjects")
    public void shouldHandleEmptyInput(RecommendationMetrics testee) {
        List<Product> zeroProducts = asList();

        final int result = testee.recoPriceSum(zeroProducts);

        assertThat(result, is(0));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldWorkForSingleProduct(RecommendationMetrics testee) {
        Product product = new Product();
        List<Product> singleProduct = asList(product);

        Recommendation recoWithCost10 = new Recommendation(1,10, false);
        List<Recommendation> recommenations = asList(recoWithCost10);

        given(recoFetcher.fetchRecosFor(product)).willReturn(recommenations);

        int result = testee.recoPriceSum(singleProduct);
        assertThat(result, is(10));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldWorkForMultipleProducts(RecommendationMetrics testee) {
        Product product = new Product();
        Product anotherProduct = new Product();
        List<Product> twoProducts = asList(product, anotherProduct);

        Recommendation recoWithCost10 = new Recommendation(1,10, false);
        List<Recommendation> firstSetOfRecos = asList(recoWithCost10);

        Recommendation recoWithCost20 = new Recommendation(2,20, false);
        List<Recommendation> secondSetOfRecos = asList(recoWithCost20);

        given(recoFetcher.fetchRecosFor(product)).willReturn(firstSetOfRecos);
        given(recoFetcher.fetchRecosFor(anotherProduct)).willReturn(secondSetOfRecos);

        int result = testee.recoPriceSum(twoProducts);
        assertThat(result, is(30));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldWorkForMultipleRecosForSameProduct(RecommendationMetrics testee) {
        Product product = new Product();
        List<Product> twoProducts = asList(product);

        Recommendation recoWithCost10 = new Recommendation(1,10, false);
        Recommendation recoWithCost20 = new Recommendation(2,20, false);
        List<Recommendation> firstSetOfRecos = asList(recoWithCost10, recoWithCost20);

        given(recoFetcher.fetchRecosFor(product)).willReturn(firstSetOfRecos);

        int result = testee.recoPriceSum(twoProducts);
        assertThat(result, is(30));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldNotConsiderSoldoutRecos(RecommendationMetrics testee) {
        Product product = new Product();
        List<Product> twoProducts = asList(product);

        Recommendation recoWithCost10 = new Recommendation(1,10, false);
        Recommendation recoWithCost20 = new Recommendation(2,20, true);
        List<Recommendation> firstSetOfRecos = asList(recoWithCost10, recoWithCost20);

        given(recoFetcher.fetchRecosFor(product)).willReturn(firstSetOfRecos);

        int result = testee.recoPriceSum(twoProducts);
        assertThat(result, is(10));
    }

    @SafeVarargs
    private final <T> List<T> asList(T... items) {
        return Arrays.asList(items);
    }
}