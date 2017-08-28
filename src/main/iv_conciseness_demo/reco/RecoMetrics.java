package iv_conciseness_demo.reco;

import iv_conciseness_demo.reco.skeletons.Product;

import java.util.List;

public interface RecoMetrics {
    int recoPriceSum(List<Product> products);
}
