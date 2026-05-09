import org.junit.Assert;
import org.junit.Test;

import main.Trade;
import main.TradingJournal;
import main.TradingJournalImpl;

/**
 * Unit tests for TradingJournalImpl.
 */
public class TradingJournal1Test {

    /** Tolerance used for floating-point assertions. */
    private static final double DELTA = 0.001;

    /** Win rate tolerance for percentage assertions. */
    private static final double WIN_RATE_DELTA = 0.01;

    /** Profit of 500 used in tests. */
    private static final double PNL_500 = 500.0;

    /** Loss of 200 used in tests. */
    private static final double PNL_NEGATIVE_200 = -200.0;

    /** Profit of 300 used in tests. */
    private static final double PNL_300 = 300.0;

    /** Profit of 100 used in tests. */
    private static final double PNL_100 = 100.0;

    /** Loss of 100 used in tests. */
    private static final double PNL_NEGATIVE_100 = -100.0;

    /** Profit of 200 used in tests. */
    private static final double PNL_200 = 200.0;

    /** Expected net P/L for the three-trade total test. */
    private static final double TOTAL_600 = 600.0;

    /** Expected win rate for two wins out of three trades. */
    private static final double WIN_RATE_TWO_THIRDS = 66.666;

    /**
     * Creates a fresh TradingJournal for each test.
     *
     * @return a new empty TradingJournalImpl
     */
    private TradingJournal createJournal() {
        return new TradingJournalImpl();
    }

    /**
     * Tests that a new journal starts with size zero.
     */
    @Test
    public void testInitialSizeZero() {
        TradingJournal journal = this.createJournal();
        Assert.assertEquals(0, journal.size());
    }

    /**
     * Tests that adding a trade increments the size.
     */
    @Test
    public void testAddTradeIncreasesSize() {
        TradingJournal journal = this.createJournal();
        journal.addTrade(new Trade("NQ", PNL_500));
        Assert.assertEquals(1, journal.size());
    }

    /**
     * Tests that removeLastTrade returns the correct trade.
     */
    @Test
    public void testRemoveLastTrade() {
        TradingJournal journal = this.createJournal();
        journal.addTrade(new Trade("NQ", PNL_500));
        Trade removed = journal.removeLastTrade();
        Assert.assertEquals("NQ", removed.getSymbol());
        Assert.assertEquals(PNL_500, removed.getProfitLoss(), DELTA);
    }

    /**
     * Tests that total P/L is computed correctly across multiple trades.
     */
    @Test
    public void testTotalProfitLoss() {
        TradingJournal journal = this.createJournal();
        journal.addTrade(new Trade("NQ", PNL_500));
        journal.addTrade(new Trade("ES", PNL_NEGATIVE_200));
        journal.addTrade(new Trade("YM", PNL_300));

        Assert.assertEquals(TOTAL_600, journal.getTotalProfitLoss(), DELTA);
    }

    /**
     * Tests that win percentage is calculated correctly.
     */
    @Test
    public void testWinPercentage() {
        TradingJournal journal = this.createJournal();
        journal.addTrade(new Trade("NQ", PNL_100));
        journal.addTrade(new Trade("ES", PNL_NEGATIVE_100));
        journal.addTrade(new Trade("YM", PNL_200));

        Assert.assertEquals(WIN_RATE_TWO_THIRDS, journal.getWinPercentage(),
                WIN_RATE_DELTA);
    }
}
