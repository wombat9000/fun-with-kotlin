package iv_conciseness_demo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class RecommendationMetricsTest {

    RecommendationMetrics testee;

    @Mock
    RecommendationFetcher recoFetcher;

    @Before
    public void setup() {
        initMocks(this);
        testee = new RecommendationMetrics(recoFetcher);
    }

    @Test
    public void shouldHandleEmptyInput() {
        List<Product> zeroProducts = asList();

        final int result = testee.recoPriceSum(zeroProducts);

        assertThat(result, is(0));
    }

    @Test
    public void shouldWorkForSingleProduct() {
        Product product = new Product();
        List<Product> singleProduct = asList(product);

        Recommendation recoWithCost10 = new Recommendation(1,10);
        List<Recommendation> recommenations = asList(recoWithCost10);

        given(recoFetcher.fetchRecosFor(product)).willReturn(recommenations);

        int result = testee.recoPriceSum(singleProduct);
        assertThat(result, is(10));
    }

    @Test
    public void shouldWorkForMultipleProducts() {
        Product product = new Product();
        Product anotherProduct = new Product();
        List<Product> twoProducts = asList(product, anotherProduct);

        Recommendation recoWithCost10 = new Recommendation(1,10);
        List<Recommendation> firstSetOfRecos = asList(recoWithCost10);

        Recommendation recoWithCost20 = new Recommendation(2,20);
        List<Recommendation> secondSetOfRecos = asList(recoWithCost20);

        given(recoFetcher.fetchRecosFor(product)).willReturn(firstSetOfRecos);
        given(recoFetcher.fetchRecosFor(anotherProduct)).willReturn(secondSetOfRecos);

        int result = testee.recoPriceSum(twoProducts);
        assertThat(result, is(30));
    }

    @Test
    public void shouldWorkForMultipleRecosForSameProduct() {
        Product product = new Product();
        List<Product> twoProducts = asList(product);

        Recommendation recoWithCost10 = new Recommendation(1,10);
        Recommendation recoWithCost20 = new Recommendation(2,20);
        List<Recommendation> firstSetOfRecos = asList(recoWithCost10, recoWithCost20);

        given(recoFetcher.fetchRecosFor(product)).willReturn(firstSetOfRecos);

        int result = testee.recoPriceSum(twoProducts);
        assertThat(result, is(30));
    }


    @SafeVarargs
    private final <T> List<T> asList(T... items) {
        return Arrays.asList(items);
    }
}