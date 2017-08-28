package iv_conciseness_demo.search;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class StreamedMetrics implements SearchMetrics {
    private final SearchEngine searchEngine;

    StreamedMetrics(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @NotNull
    @Override
    public Map<Produkt, Long> fetchTopTwoResultCounts(@NotNull final List<String> searchTerms) {
        return searchTerms.stream()
                .map(searchEngine::searchFor)
                .filter(Objects::nonNull)
                .flatMap(results -> results.stream().limit(2))
                .collect(groupingBy(identity(), counting()));
    }
}
