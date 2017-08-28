package iv_conciseness_demo.search;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public class LoopedMetrics implements SearchMetrics {


    @NotNull
    @Override
    public Map<Produkt, Integer> fetchResultCounts(@NotNull List<String> searchTerms) {
        return Collections.emptyMap();
    }
}
