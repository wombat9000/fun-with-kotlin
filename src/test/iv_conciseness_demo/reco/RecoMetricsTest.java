package iv_conciseness_demo.reco;

import iv_conciseness_demo.reco.skeletons.Product;
import iv_conciseness_demo.reco.skeletons.Recommendation;
import iv_conciseness_demo.reco.skeletons.RecommendationFetcher;
import org.mockito.Mock;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class RecoMetricsTest {

    @Mock
    private RecommendationFetcher recoFetcher;

    @DataProvider(name = "testSubjects")
    public Object[][] testSubjects() {
        initMocks(this);

        return new Object[][] {
                {new LoopedMetrics(recoFetcher)},
                {new StreamedMetrics(recoFetcher)},
                {new KotlinMetrics(recoFetcher)},
        };
    }

    @Test(dataProvider = "testSubjects")
    public void shouldHandleEmptyInput(final RecoMetrics testee) {
        final List<Product> zeroProducts = asList();

        final int result = testee.recoPriceSum(zeroProducts);

        assertThat(result, is(0));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldWorkForSingleProduct(final RecoMetrics testee) {
        final Product product = new Product();
        final List<Product> singleProduct = asList(product);

        final Recommendation recoWithCost10 = new Recommendation(1,10, false);
        final List<Recommendation> recommenations = asList(recoWithCost10);

        given(recoFetcher.fetchRecosFor(product)).willReturn(recommenations);

        final int result = testee.recoPriceSum(singleProduct);
        assertThat(result, is(10));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldWorkForMultipleProducts(final RecoMetrics testee) {
        final Product product = new Product();
        final Product anotherProduct = new Product();
        final List<Product> twoProducts = asList(product, anotherProduct);

        final Recommendation recoWithCost10 = new Recommendation(1,10, false);
        final List<Recommendation> firstSetOfRecos = asList(recoWithCost10);

        final Recommendation recoWithCost20 = new Recommendation(2,20, false);
        final List<Recommendation> secondSetOfRecos = asList(recoWithCost20);

        given(recoFetcher.fetchRecosFor(product)).willReturn(firstSetOfRecos);
        given(recoFetcher.fetchRecosFor(anotherProduct)).willReturn(secondSetOfRecos);

        final int result = testee.recoPriceSum(twoProducts);
        assertThat(result, is(30));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldWorkForMultipleRecosForSameProduct(final RecoMetrics testee) {
        final Product product = new Product();
        final List<Product> twoProducts = asList(product);

        final Recommendation recoWithCost10 = new Recommendation(1,10, false);
        final Recommendation recoWithCost20 = new Recommendation(2,20, false);
        final List<Recommendation> firstSetOfRecos = asList(recoWithCost10, recoWithCost20);

        given(recoFetcher.fetchRecosFor(product)).willReturn(firstSetOfRecos);

        final int result = testee.recoPriceSum(twoProducts);
        assertThat(result, is(30));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldNotConsiderSoldoutRecos(final RecoMetrics testee) {
        final Product product = new Product();
        final List<Product> twoProducts = asList(product);

        final Recommendation recoWithCost10 = new Recommendation(1,10, false);
        final Recommendation recoWithCost20 = new Recommendation(2,20, true);
        final List<Recommendation> firstSetOfRecos = asList(recoWithCost10, recoWithCost20);

        given(recoFetcher.fetchRecosFor(product)).willReturn(firstSetOfRecos);

        final int result = testee.recoPriceSum(twoProducts);
        assertThat(result, is(10));
    }

}