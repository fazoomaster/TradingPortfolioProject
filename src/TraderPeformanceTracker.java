public final class TraderPerformanceTracker {

    private TradingJournal journal;

    public TraderPerformanceTracker() {
        this.journal = new TradingJournal1();
    }

    public void recordTrade(String symbol, double pnl) {
        this.journal.addTrade(new TradingJournal1.Trade1(symbol, pnl));
    }

    public double getNetPerformance() {
        return this.journal.getTotalProfitLoss();
    }

    public double getWinRate() {
        return this.journal.getWinPercentage();
    }

    public static void main(String[] args) {

        TraderPerformanceTracker tracker = new TraderPerformanceTracker();

        tracker.recordTrade("NQ", 400);
        tracker.recordTrade("ES", -150);
        tracker.recordTrade("YM", 250);

        System.out.println("Net P/L: " + tracker.getNetPerformance());
        System.out.println("Win Rate: " + tracker.getWinRate());
    }
}
