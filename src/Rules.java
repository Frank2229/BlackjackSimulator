public class Rules {
    private boolean isDAS;
    private boolean isS17;
    private boolean isSurrender;
    private double blackjackPayout;
    private double penetration;
    private int burnCards;
    private int doubleLimit;
    private int tableMax;
    private int tableMin;
    private int totalDecks;

    public Rules(boolean isDAS, boolean isS17, boolean isSurrender, double blackjackPayout, double penetration, int burnCards, int doubleLimit, int tableMax, int tableMin, int totalDecks) {
        this.isDAS = isDAS;
        this.isS17 = isS17;
        this.isSurrender = isSurrender;
        this.blackjackPayout = blackjackPayout;
        this.penetration = penetration;
        this.burnCards = burnCards;
        this.doubleLimit = doubleLimit;
        this.tableMax = tableMax;
        this.tableMin = tableMin;
        this.totalDecks = totalDecks;
    }

    public double getBlackjackPayout() {
        return blackjackPayout;
    }

    public int getBurnCards() {
        return burnCards;
    }

    public int getDoubleLimit() {
        return doubleLimit;
    }

    public double getPenetration() {
        return penetration;
    }

    public int getTableMax() {
        return tableMax;
    }

    public int getTableMin() {
        return tableMin;
    }

    public int getTotalDecks() {
        return totalDecks;
    }

    public boolean isDAS() {
        return isDAS;
    }

    public boolean isS17() {
        return isS17;
    }

    public boolean isSurrender() {
        return isSurrender;
    }
}
