package main;

/**
 * Represents a single trade entry.
 */
public class Trade {

    /** The ticker symbol for the traded instrument. */
    private final String symbol;

    /** The profit or loss value for this trade. */
    private final double profitLoss;

    /**
     * Constructs a trade with the given symbol and P/L.
     *
     * @param symbol     the ticker symbol
     * @param profitLoss the profit or loss amount
     */
    public Trade(String symbol, double profitLoss) {
        this.symbol = symbol;
        this.profitLoss = profitLoss;
    }

    /**
     * Returns the ticker symbol for this trade.
     *
     * @return the symbol
     */
    public final String getSymbol() {
        return this.symbol;
    }

    /**
     * Returns the profit or loss for this trade.
     *
     * @return the profit/loss amount
     */
    public final double getProfitLoss() {
        return this.profitLoss;
    }

    /**
     * Returns true if this trade was profitable.
     *
     * @return true if profit/loss is positive
     */
    public final boolean isWin() {
        return this.profitLoss > 0;
    }
}
