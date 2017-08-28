package iv_conciseness_demo.search;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoopedMetrics implements SearchMetrics {

    private final SearchEngine searchEngine;

    LoopedMetrics(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @NotNull
    @Override
    public Map<Produkt, Long> fetchTopTwoResultCounts(@NotNull final List<String> searchTerms) {
        final Map<Produkt, Long> result = new HashMap<>();

        for(String searchTerm: searchTerms) {
            final List<Produkt> produkts = searchEngine.searchFor(searchTerm);

            if(produkts == null) {
                continue;
            }

            for (int i = 0, produktsSize = produkts.size(); i < produktsSize; i++) {
                final Produkt produkt = produkts.get(i);
                final Long currentCount = result.getOrDefault(produkt, 0L);
                result.put(produkt, currentCount + 1);
                if (i == 1) {
                    break;
                }
            }
        }

        return result;
    }
}
