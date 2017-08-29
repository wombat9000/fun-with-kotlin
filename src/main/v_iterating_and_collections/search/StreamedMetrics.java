package v_iterating_and_collections.search;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

public class StreamedMetrics implements SearchMetrics {
    private final SearchEngine searchEngine;

    StreamedMetrics(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @NotNull
    @Override
    public Map<Produkt, Integer> fetchTopTwoResultCounts(@NotNull final List<String> searchTerms) {
        return searchTerms.stream()
                .map(searchEngine::searchFor)
                .filter(Objects::nonNull)
                .flatMap(results -> results.stream().limit(2))
                .collect(groupingBy(identity(), counting()));
    }

    @NotNull
    private Collector<Produkt, ?, Integer> counting() {
        return reducing(0, e -> 1, Integer::sum);
    }
}
