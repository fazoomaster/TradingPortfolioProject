import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TradingJournalSecondaryTest {

    private static final double DELTA = 0.001;

    private static final double POSITIVE_100 = 100.0;
    private static final double POSITIVE_200 = 200.0;
    private static final double POSITIVE_300 = 300.0;
    private static final double POSITIVE_500 = 500.0;

    private static final double NEGATIVE_100 = -100.0;
    private static final double NEGATIVE_200 = -200.0;

    /**
     * Creates a new journal instance.
     *
     * @return new TradingJournal
     */
    private TradingJournal createJournal() {
        return new TradingJournal1();
    }

    /**
     * Creates a trade record.
     *
     * @param symbol
     *            trade symbol
     * @param pnl
     *            profit or loss
     * @return trade instance
     */
    private TradingJournalKernel.Trade trade(String symbol, double pnl) {
        return new TradingJournal1.Trade1(symbol, pnl);
    }

    @Test
    public void testTotalProfitLossMixedTrades() {
        TradingJournal j = this.createJournal();
        j.addTrade(this.trade("NQ", POSITIVE_500));
        j.addTrade(this.trade("ES", NEGATIVE_200));
        j.addTrade(this.trade("YM", POSITIVE_300));

        assertEquals(600.0, j.getTotalProfitLoss(), DELTA);
    }

    @Test
    public void testTotalProfitLossAllLosses() {
        TradingJournal j = this.createJournal();
        j.addTrade(this.trade("NQ", NEGATIVE_100));
        j.addTrade(this.trade("ES", NEGATIVE_200));

        assertEquals(-300.0, j.getTotalProfitLoss(), DELTA);
    }

    @Test
    public void testWinPercentageAllWins() {
        TradingJournal j = this.createJournal();
        j.addTrade(this.trade("NQ", POSITIVE_100));
        j.addTrade(this.trade("ES", POSITIVE_200));

        assertEquals(100.0, j.getWinPercentage(), DELTA);
    }

    @Test
    public void testWinPercentageAllLosses() {
        TradingJournal j = this.createJournal();
        j.addTrade(this.trade("NQ", NEGATIVE_100));
        j.addTrade(this.trade("ES", NEGATIVE_200));

        assertEquals(0.0, j.getWinPercentage(), DELTA);
    }

    @Test
    public void testWinPercentageMixed() {
        TradingJournal j = this.createJournal();
        j.addTrade(this.trade("NQ", POSITIVE_100));
        j.addTrade(this.trade("ES", NEGATIVE_100));
        j.addTrade(this.trade("YM", POSITIVE_200));

        assertEquals(66.666, j.getWinPercentage(), 0.01);
    }

    @Test
    public void testSecondaryMethodsDoNotModifyJournal() {
        TradingJournal j = this.createJournal();
        j.addTrade(this.trade("NQ", POSITIVE_100));
        j.addTrade(this.trade("ES", NEGATIVE_100));

        int originalSize = j.size();

        j.getTotalProfitLoss();
        j.getWinPercentage();

        assertEquals(originalSize, j.size());
    }
}
