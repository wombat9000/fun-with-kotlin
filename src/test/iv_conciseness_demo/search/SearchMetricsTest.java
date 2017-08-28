package iv_conciseness_demo.search;

import org.mockito.Mock;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.AssertJUnit.assertFalse;

public class SearchMetricsTest {

    @Mock
    private SearchEngine searchEngine;

    @DataProvider(name = "testSubjects")
    public Object[][] testSubjects() {
        initMocks(this);

        return new Object[][] {
                {new LoopedMetrics(searchEngine)},
                {new StreamedMetrics(searchEngine)},
                {new KotlinMetrics(searchEngine)},
        };
    }

    @Test(dataProvider = "testSubjects")
    public void shouldHandleEmptyInput(SearchMetrics testee) {
        Map<Produkt, Integer> result = testee.fetchTopTwoResultCounts(emptyList());

        assertThat(result, is(emptyMap()));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldHandleSingleTermWithSingleResult(SearchMetrics testee) {
        Produkt someProdukt = new Produkt("someProdukt");
        given(searchEngine.searchFor("someTerm")).willReturn(singletonList(someProdukt));

        Map<Produkt, Integer> result = testee.fetchTopTwoResultCounts(singletonList("someTerm"));

        assertThat(result, is(singletonMap(someProdukt, 1)));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldHandleTwoTermsWithSameResult(SearchMetrics testee) {
        Produkt someProdukt = new Produkt("someProdukt");
        given(searchEngine.searchFor("someTerm")).willReturn(singletonList(someProdukt));
        given(searchEngine.searchFor("anotherTerm")).willReturn(singletonList(someProdukt));

        Map<Produkt, Integer> result = testee.fetchTopTwoResultCounts(asList("someTerm", "anotherTerm"));

        assertThat(result, is(singletonMap(someProdukt, 2)));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldHandleSingleTermWithMultipleResults(SearchMetrics testee) {
        Produkt someProdukt = new Produkt("someProdukt");
        Produkt anotherProdukt = new Produkt("anotherProdukt");
        given(searchEngine.searchFor("someTerm")).willReturn(asList(someProdukt, anotherProdukt));

        Map<Produkt, Integer> result = testee.fetchTopTwoResultCounts(asList("someTerm"));

        assertThat(result.get(someProdukt), is(1));
        assertThat(result.get(anotherProdukt), is(1));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldOnlyConsiderFirstTwoResults(SearchMetrics testee) {
        Produkt someProdukt = new Produkt("someProdukt");
        Produkt anotherProdukt = new Produkt("anotherProdukt");
        Produkt disregardedProdukt = new Produkt("disregardedProdukt");
        given(searchEngine.searchFor("someTerm")).willReturn(asList(someProdukt, anotherProdukt, disregardedProdukt));

        Map<Produkt, Integer> result = testee.fetchTopTwoResultCounts(asList("someTerm"));

        assertThat(result.get(someProdukt), is(1));
        assertThat(result.get(anotherProdukt), is(1));
        assertFalse(result.containsKey(disregardedProdukt));
    }

    @Test(dataProvider = "testSubjects")
    public void shouldHandleMissingResponses(SearchMetrics testee) {
        Produkt someProdukt = new Produkt("someProdukt");
        given(searchEngine.searchFor("someTerm")).willReturn(null);
        given(searchEngine.searchFor("anotherTerm")).willReturn(asList(someProdukt));

        Map<Produkt, Integer> result = testee.fetchTopTwoResultCounts(asList("someTerm", "anotherTerm"));

        assertThat(result.get(someProdukt), is(1));
    }
}