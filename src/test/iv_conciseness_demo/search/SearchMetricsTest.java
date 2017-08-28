package iv_conciseness_demo.search;

import org.testng.annotations.Test;

import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SearchMetricsTest {

    @Test
    public void shouldHandleEmptyInput() {
        LoopedMetrics testee = new LoopedMetrics();

        Map<Produkt, Integer> resultWithCount = testee.fetchResultCounts(emptyList());

        assertThat(resultWithCount, is(emptyMap()));
    }
}