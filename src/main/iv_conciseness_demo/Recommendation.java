package iv_conciseness_demo;

public class Recommendation {
    private final int id;
    private final int cost;

    public Recommendation(final int id, final int cost) {
        this.id = id;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }
}
