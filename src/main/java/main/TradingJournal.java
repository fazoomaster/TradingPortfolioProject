package main;

import java.util.List;

/**
 * Interface representing a trading journal that stores trades
 * and provides performance analytics.
 */
public interface TradingJournal {

    /**
     * Adds a trade to the journal.
     *
     * @param trade the trade to add
     */
    void addTrade(Trade trade);

    /**
     * Removes and returns the most recently added trade.
     *
     * @return the last trade in the journal
     */
    Trade removeLastTrade();

    /**
     * Returns the number of trades in the journal.
     *
     * @return trade count
     */
    int size();

    /**
     * Returns the sum of all trade profit/loss values.
     *
     * @return total profit or loss
     */
    double getTotalProfitLoss();

    /**
     * Returns the percentage of trades that were wins.
     *
     * @return win rate as a percentage (0 to 100)
     */
    double getWinPercentage();

    /**
     * Returns an unmodifiable view of all trades.
     *
     * @return list of all trades
     */
    List<Trade> getAllTrades();
}
