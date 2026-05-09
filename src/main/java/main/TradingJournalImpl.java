package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Concrete implementation of the TradingJournal interface.
 * Stores trades internally using an ArrayList.
 */
public class TradingJournalImpl implements TradingJournal {

    /** Multiplier used to convert a ratio to a percentage. */
    private static final double PERCENT = 100.0;

    /** Internal list holding all recorded trades. */
    private final List<Trade> trades;

    /**
     * Constructs an empty trading journal.
     */
    public TradingJournalImpl() {
        this.trades = new ArrayList<>();
    }

    /**
     * Adds a trade to the journal.
     *
     * @param trade the trade to add
     */
    @Override
    public final void addTrade(Trade trade) {
        if (trade == null) {
            throw new IllegalArgumentException("Trade cannot be null");
        }
        this.trades.add(trade);
    }

    /**
     * Removes and returns the most recently added trade.
     *
     * @return the last trade in the journal
     */
    @Override
    public final Trade removeLastTrade() {
        if (this.trades.isEmpty()) {
            throw new IllegalStateException("No trades to remove");
        }
        return this.trades.remove(this.trades.size() - 1);
    }

    /**
     * Returns the number of trades in the journal.
     *
     * @return trade count
     */
    @Override
    public final int size() {
        return this.trades.size();
    }

    /**
     * Returns the sum of all trade profit/loss values.
     *
     * @return total profit or loss
     */
    @Override
    public final double getTotalProfitLoss() {
        return this.trades.stream()
                .mapToDouble(Trade::getProfitLoss)
                .sum();
    }

    /**
     * Returns the percentage of trades that were wins.
     *
     * @return win rate as a percentage (0 to 100)
     */
    @Override
    public final double getWinPercentage() {
        if (this.trades.isEmpty()) {
            return 0.0;
        }

        long wins = this.trades.stream()
                .filter(Trade::isWin)
                .count();

        return (wins * PERCENT) / this.trades.size();
    }

    /**
     * Returns an unmodifiable view of all trades.
     *
     * @return list of all trades
     */
    @Override
    public final List<Trade> getAllTrades() {
        return Collections.unmodifiableList(this.trades);
    }
}
