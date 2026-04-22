/**
 * Demonstrates how the TradingJournal component can be embedded inside a
 * higher-level performance tracking system.
 */
public final class TraderPerformanceTracker {

    /** Underlying trading journal component. */
    private final TradingJournal journal;

    /** Example trade profit/loss values used in main. */
    private static final double SAMPLE_TRADE_ONE = 400.0;
    /** Example trade profit/loss values used in main. */
    private static final double SAMPLE_TRADE_TWO = -150.0;
    /** Example trade profit/loss values used in main. */
    private static final double SAMPLE_TRADE_THREE = 250.0;

    /**
     * Constructs a new performance tracker with an empty journal.
     */
    public TraderPerformanceTracker() {
        this.journal = new TradingJournal1();
    }

    /**
     * Records a trade into the journal.
     *
     * @param symbol
     *            the instrument traded
     * @param pnl
     *            the profit or loss value
     */
    public void recordTrade(String symbol, double pnl) {

        TradingJournalKernel.Trade trade = new TradingJournal1.Trade1(symbol,
                pnl);

        this.journal.addTrade(trade);
    }

    /**
     * Returns total net performance across all trades.
     *
     * @return total profit or loss
     */
    public double getNetPerformance() {
        return this.journal.getTotalProfitLoss();
    }

    /**
     * Returns win rate percentage.
     *
     * @return win percentage in range [0, 100]
     */
    public double getWinRate() {
        return this.journal.getWinPercentage();
    }

    /**
     * Demonstrates usage of the performance tracker.
     *
     * @param args
     *            command-line arguments (unused)
     */
    public static void main(String[] args) {

        TraderPerformanceTracker tracker = new TraderPerformanceTracker();

        tracker.recordTrade("NQ", SAMPLE_TRADE_ONE);
        tracker.recordTrade("ES", SAMPLE_TRADE_TWO);
        tracker.recordTrade("YM", SAMPLE_TRADE_THREE);

        System.out.println("Net P/L: " + tracker.getNetPerformance());
        System.out.println("Win Rate: " + tracker.getWinRate());
    }
}
