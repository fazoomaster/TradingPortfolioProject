import components.standard.Standard;

/**
 * Kernel interface for TradingJournal component.
 */
public interface TradingJournalKernel extends Standard<TradingJournal> {

    /**
     * Represents a single trade entry.
     */
    public interface Trade {

        /**
         * @return the trading symbol
         */
        String symbol();

        /**
         * @return the profit or loss value
         */
        double profitLoss();
    }

    /**
     * Adds a trade to this journal.
     *
     * @param trade
     *            the trade to add
     * @updates this
     * @requires trade is not null
     * @ensures size() = #size() + 1
     */
    void addTrade(Trade trade);

    /**
     * Removes and returns the most recently added trade.
     *
     * @return the removed trade
     * @updates this
     * @requires size() > 0
     * @ensures size() = #size() - 1
     */
    Trade removeLastTrade();

    /**
     * Reports the number of trades in this journal.
     *
     * @return the number of recorded trades
     * @ensures size() = the number of trades currently stored in this
     */
    int size();
}
