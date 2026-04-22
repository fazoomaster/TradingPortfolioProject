/**
 * Secondary abstract class for TradingJournal component.
 */
public abstract class TradingJournalSecondary implements TradingJournal {

    /** Multiplier used to convert ratio to percentage. */
    private static final double PERCENT_MULTIPLIER = 100.0;

    @Override
    public final double getTotalProfitLoss() {

        double total = 0.0;
        TradingJournal temp = this.newInstance();

        while (this.size() > 0) {
            TradingJournalKernel.Trade trade = this.removeLastTrade();
            total += trade.profitLoss();
            temp.addTrade(trade);
        }

        while (temp.size() > 0) {
            this.addTrade(temp.removeLastTrade());
        }

        return total;
    }

    @Override
    public final double getWinPercentage() {

        int wins = 0;
        int totalTrades = this.size();
        TradingJournal temp = this.newInstance();

        while (this.size() > 0) {
            TradingJournalKernel.Trade trade = this.removeLastTrade();

            if (trade.profitLoss() > 0) {
                wins++;
            }

            temp.addTrade(trade);
        }

        while (temp.size() > 0) {
            this.addTrade(temp.removeLastTrade());
        }

        if (totalTrades == 0) {
            return 0.0;
        }

        return (wins * PERCENT_MULTIPLIER) / totalTrades;
    }
}
