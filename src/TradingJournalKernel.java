import components.standard.Standard;

/**
 * Kernel interface for TradingJournal component.
 */
public interface TradingJournalKernel extends Standard<TradingJournal> {

    /**
     * Adds a trade to this journal.
     *
     * @param symbol
     *            the trading instrument symbol (e.g., "NQ", "ES")
     * @param profitLoss
     *            the profit or loss value of the trade
     * @updates this
     * @requires symbol is not null
     * @ensures size() = #size() + 1
     */
    void addTrade(String symbol, double profitLoss);

    /**
     * Removes and returns the most recently added trade.
     *
     * @return the profit/loss of the removed trade
     * @updates this
     * @requires size() > 0
     * @ensures size() = #size() - 1
     */
    double removeLastTrade();

    /**
     * Reports the number of trades in this journal.
     *
     * @return the number of recorded trades
     * @ensures size = |this|
     */
    int size();

}
