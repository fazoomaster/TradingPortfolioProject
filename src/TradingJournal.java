/**
 * Interface for TradingJournal component.
 *
 * <p>
 * This interface layers analytical functionality on top of the
 * TradingJournalKernel.
 * </p>
 */
public interface TradingJournal extends TradingJournalKernel {

    /**
     * Reports the total profit or loss across all trades.
     *
     * @return the sum of all trade profit/loss values
     * @ensures getTotalProfitLoss = sum of all recorded trade values
     */
    double getTotalProfitLoss();

    /**
     * Reports the percentage of winning trades.
     *
     * @return the win percentage in the range [0, 100]
     * @requires size() > 0
     * @ensures 0 <= getWinPercentage <= 100
     */
    double getWinPercentage();

}

