import java.util.ArrayList;
import java.util.List;

/**
 * A simple standalone implementation of a trading journal. This MVP version
 * stores trades in an ArrayList and provides basic performance metrics.
 */
public final class TradingJournalMVP {

    /** Multiplier used to compute percentage values. */
    private static final double PERCENT_MULTIPLIER = 100.0;

    /** Sample trade values for demonstration. */
    private static final double SAMPLE_TRADE_ONE = 250.50;
    /** Sample trade values for demonstration. */
    private static final double SAMPLE_TRADE_TWO = -100.00;
    /** Sample trade values for demonstration. */
    private static final double SAMPLE_TRADE_THREE = 175.75;
    /** Sample trade values for demonstration. */
    private static final double SAMPLE_TRADE_FOUR = -50.25;

    /** Internal list of recorded trade entries. */
    private final List<TradeEntry> entries;

    /**
     * Constructs an empty trading journal.
     */
    public TradingJournalMVP() {
        this.entries = new ArrayList<>();
    }

    /**
     * Adds a trade to the journal.
     *
     * @param symbol
     *            the instrument traded
     * @param profitLoss
     *            the profit or loss amount
     */
    public void addTrade(String symbol, double profitLoss) {
        TradeEntry entry = new TradeEntry(symbol, profitLoss);
        this.entries.add(entry);
    }

    /**
     * Returns total profit or loss across all trades.
     *
     * @return total profit/loss
     */
    public double getTotalProfitLoss() {
        double total = 0.0;
        for (TradeEntry entry : this.entries) {
            total += entry.getProfitLoss();
        }
        return total;
    }

    /**
     * Returns win percentage in range [0, 100].
     *
     * @return win percentage
     */
    public double getWinPercentage() {
        if (this.entries.isEmpty()) {
            return 0.0;
        }

        int wins = 0;
        for (TradeEntry entry : this.entries) {
            if (entry.isWin()) {
                wins++;
            }
        }

        return (wins * PERCENT_MULTIPLIER) / this.entries.size();
    }

    /**
     * Returns number of trades recorded.
     *
     * @return journal size
     */
    public int size() {
        return this.entries.size();
    }

    /**
     * Represents a single trade entry.
     */
    private static final class TradeEntry {

        /** Trade symbol (e.g., NQ, ES). */
        private final String symbol;

        /** Profit or loss amount. */
        private final double profitLoss;

        /**
         * Constructs a trade entry.
         *
         * @param symbol
         *            trade symbol
         * @param profitLoss
         *            profit or loss amount
         */
        TradeEntry(String symbol, double profitLoss) {
            this.symbol = symbol;
            this.profitLoss = profitLoss;
        }

        /**
         * Returns trade symbol.
         *
         * @return symbol
         */
        public String getSymbol() {
            return this.symbol;
        }

        /**
         * Returns profit or loss amount.
         *
         * @return profit/loss
         */
        public double getProfitLoss() {
            return this.profitLoss;
        }

        /**
         * Returns whether the trade was profitable.
         *
         * @return true if profit > 0
         */
        public boolean isWin() {
            return this.profitLoss > 0;
        }
    }

    /**
     * Demonstrates usage of the MVP trading journal.
     *
     * @param args
     *            command-line arguments (unused)
     */
    public static void main(String[] args) {

        TradingJournalMVP journal = new TradingJournalMVP();

        journal.addTrade("NQ", SAMPLE_TRADE_ONE);
        journal.addTrade("ES", SAMPLE_TRADE_TWO);
        journal.addTrade("NQ", SAMPLE_TRADE_THREE);
        journal.addTrade("NQ", SAMPLE_TRADE_FOUR);

        System.out.println("Number of trades: " + journal.size());
        System.out
                .println("Total Profit/Loss: $" + journal.getTotalProfitLoss());
        System.out
                .println("Win Percentage: " + journal.getWinPercentage() + "%");
    }
}
