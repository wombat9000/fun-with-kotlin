package iv_conciseness_demo.reco.skeletons;

public class Recommendation {
    private final int id;
    private final int cost;
    private boolean soldout;

    public Recommendation(final int id, final int cost, boolean soldout) {
        this.id = id;
        this.cost = cost;
        this.soldout = soldout;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return !soldout;
    }
}
