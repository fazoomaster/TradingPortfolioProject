package main;

/**
 * Demonstrates standalone usage of the TradingJournal component.
 */
public final class TradingJournalCLI {

    /** Sample winning trade on NQ. */
    private static final double POSITIVE_500 = 500.0;

    /** Sample losing trade on ES. */
    private static final double NEGATIVE_200 = -200.0;

    /** Sample winning trade on YM. */
    private static final double POSITIVE_300 = 300.0;

    /**
     * Prevent instantiation of this utility class.
     */
    private TradingJournalCLI() {
    }

    /**
     * Entry point demonstrating journal usage.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        TradingJournal journal = new TradingJournalImpl();

        journal.addTrade(new Trade("NQ", POSITIVE_500));
        journal.addTrade(new Trade("ES", NEGATIVE_200));
        journal.addTrade(new Trade("YM", POSITIVE_300));

        System.out.println("Number of trades: " + journal.size());
        System.out.println("Total P/L: " + journal.getTotalProfitLoss());
        System.out.println("Win %: " + journal.getWinPercentage());
    }
}
