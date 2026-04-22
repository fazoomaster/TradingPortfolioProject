/**
 * Demonstrates standalone usage of the TradingJournal component through a
 * simple command-line interface.
 */
public final class TradingJournalCLI {

    /** Sample trade values used for demonstration. */
    private static final double POSITIVE_500 = 500.0;
    /** Sample trade values used for demonstration. */
    private static final double NEGATIVE_200 = -200.0;
    /** Sample trade values used for demonstration. */
    private static final double POSITIVE_300 = 300.0;

    /**
     * Private constructor to prevent instantiation.
     */
    private TradingJournalCLI() {
    }

    /**
     * Demonstrates adding trades and printing metrics.
     *
     * @param args
     *            command-line arguments (unused)
     */
    public static void main(String[] args) {

        TradingJournal journal = new TradingJournal1();

        journal.addTrade(new TradingJournal1.Trade1("NQ", POSITIVE_500));
        journal.addTrade(new TradingJournal1.Trade1("ES", NEGATIVE_200));
        journal.addTrade(new TradingJournal1.Trade1("YM", POSITIVE_300));

        System.out.println("Number of trades: " + journal.size());
        System.out.println("Total P/L: " + journal.getTotalProfitLoss());
        System.out.println("Win %: " + journal.getWinPercentage());
    }
}
