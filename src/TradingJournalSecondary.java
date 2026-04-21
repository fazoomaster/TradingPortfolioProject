/**
 * Secondary abstract class for TradingJournal component.
 */
public abstract class TradingJournalSecondary implements TradingJournal {

    @Override
    public final double getTotalProfitLoss() {

        double total = 0;

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
            return 0;
        }

        return (wins * 100.0) / totalTrades;
    }

    @Override
    public final String toString() {

        StringBuilder result = new StringBuilder();
        result.append("[");

        TradingJournal temp = this.newInstance();

        boolean first = true;

        while (this.size() > 0) {
            TradingJournalKernel.Trade trade = this.removeLastTrade();

            if (!first) {
                result.append(", ");
            }

            result.append(trade.symbol());
            result.append(": ");
            result.append(trade.profitLoss());

            first = false;

            temp.addTrade(trade);
        }

        while (temp.size() > 0) {
            this.addTrade(temp.removeLastTrade());
        }

        result.append("]");

        return result.toString();
    }
}
