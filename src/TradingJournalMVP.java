import java.util.ArrayList;
import java.util.List;

// class
public final class TradingJournalMVP {

    private static final double PERCENT_MULTIPLIER = 100.0;

    // Representation list of trade records
    private List<TradeEntry> entries;

    // Constructor
    public TradingJournalMVP() {
        this.entries = new ArrayList<>();
    }

    // Adds a new trade to the journal
    public void addTrade(String symbol, double profitLoss) {
        TradeEntry entry = new TradeEntry(symbol, profitLoss);
        this.entries.add(entry);
    }

    // Returns total profit/loss across all trades
    public double getTotalProfitLoss() {
        double total = 0;
        for (TradeEntry entry : this.entries) {
            total += entry.getProfitLoss();
        }
        return total;
    }

    // Returns win percentage (0–100)
    public double getWinPercentage() {
        if (this.entries.isEmpty()) {
            return 0;
        }

        int wins = 0;
        for (TradeEntry entry : this.entries) {
            if (entry.isWin()) {
                wins++;
            }
        }

        return (wins * PERCENT_MULTIPLIER) / this.entries.size();
    }

    // Returns number of trades recorded
    public int size() {
        return this.entries.size();
    }

    //Internal class representing a single trade
    private static class TradeEntry {
        private String symbol; // e.g., NQ, ES
        private double profitLoss; // money made or lost
        private boolean win; // true if profitable

        TradeEntry(String symbol, double profitLoss) {
            this.symbol = symbol;
            this.profitLoss = profitLoss;
            this.win = profitLoss > 0;
        }

        public String getSymbol() {
            return this.symbol;
        }

        public double getProfitLoss() {
            return this.profitLoss;
        }

        public boolean isWin() {
            return this.win;
        }

    }

    public static void main(String[] args) {

        double trade1 = 250.50;
        double trade2 = -100.00;
        double trade3 = 175.75;
        double trade4 = -50.25;

        TradingJournalMVP journal = new TradingJournalMVP();

        // Add sample trades
        journal.addTrade("NQ", trade1);
        journal.addTrade("ES", trade2);
        journal.addTrade("NQ", trade3);
        journal.addTrade("NQ", trade4);

        System.out.println("Number of trades: " + journal.size());
        System.out
                .println("Total Profit/Loss: $" + journal.getTotalProfitLoss());
        System.out
                .println("Win Percentage: " + journal.getWinPercentage() + "%");

    }
}
