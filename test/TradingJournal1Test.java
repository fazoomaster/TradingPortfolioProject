import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TradingJournal1Test {

    private static final double DELTA = 0.001;

    private static final double POSITIVE_500 = 500.0;
    private static final double POSITIVE_300 = 300.0;
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
    public void testInitialSizeZero() {
        TradingJournal j = this.createJournal();
        assertEquals(0, j.size());
    }

    @Test
    public void testAddTradeIncreasesSize() {
        TradingJournal j = this.createJournal();
        j.addTrade(this.trade("NQ", POSITIVE_500));
        assertEquals(1, j.size());
    }

    @Test
    public void testRemoveLastTradeDecreasesSize() {
        TradingJournal j = this.createJournal();
        j.addTrade(this.trade("NQ", POSITIVE_500));
        j.removeLastTrade();
        assertEquals(0, j.size());
    }

    @Test
    public void testRemoveLastTradeReturnsCorrectTrade() {
        TradingJournal j = this.createJournal();
        j.addTrade(this.trade("NQ", POSITIVE_500));

        TradingJournalKernel.Trade t = j.removeLastTrade();

        assertEquals("NQ", t.symbol());
        assertEquals(POSITIVE_500, t.profitLoss(), DELTA);
    }

    @Test
    public void testClearResetsJournal() {
        TradingJournal j = this.createJournal();
        j.addTrade(this.trade("NQ", POSITIVE_500));
        j.clear();
        assertEquals(0, j.size());
    }

    @Test
    public void testNewInstanceCreatesEmptyJournal() {
        TradingJournal j = this.createJournal();
        TradingJournal newJ = j.newInstance();
        assertEquals(0, newJ.size());
    }

    @Test
    public void testTransferFromMovesTrades() {
        TradingJournal source = this.createJournal();
        TradingJournal target = this.createJournal();

        source.addTrade(this.trade("NQ", POSITIVE_500));
        source.addTrade(this.trade("ES", NEGATIVE_200));

        target.transferFrom(source);

        assertEquals(0, source.size());
        assertEquals(2, target.size());
    }

    @Test
    public void testMultipleAdds() {
        TradingJournal j = this.createJournal();
        j.addTrade(this.trade("NQ", POSITIVE_500));
        j.addTrade(this.trade("ES", NEGATIVE_200));
        j.addTrade(this.trade("YM", POSITIVE_300));
        assertEquals(3, j.size());
    }
}
