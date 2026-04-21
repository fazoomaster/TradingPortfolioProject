public class TradingJournal1Test {

    private TradingJournal createJournal() {
        return new TradingJournal1();
    }

    private TradingJournalKernel.Trade trade(String symbol, double pnl) {
        return new TradingJournal1.Trade1(symbol, pnl);
    }

    @Test
    public void testInitialSizeZero() {
        TradingJournal j = createJournal();
        assertEquals(0, j.size());
    }

    @Test
    public void testAddTradeIncreasesSize() {
        TradingJournal j = createJournal();
        j.addTrade(trade("NQ", 500));
        assertEquals(1, j.size());
    }

    @Test
    public void testRemoveLastTradeDecreasesSize() {
        TradingJournal j = createJournal();
        j.addTrade(trade("NQ", 500));
        j.removeLastTrade();
        assertEquals(0, j.size());
    }

    @Test
    public void testRemoveLastTradeReturnsCorrectTrade() {
        TradingJournal j = createJournal();
        j.addTrade(trade("NQ", 500));
        TradingJournalKernel.Trade t = j.removeLastTrade();
        assertEquals("NQ", t.symbol());
        assertEquals(500, t.profitLoss(), 0.001);
    }

    @Test
    public void testClearResetsJournal() {
        TradingJournal j = createJournal();
        j.addTrade(trade("NQ", 500));
        j.clear();
        assertEquals(0, j.size());
    }

    @Test
    public void testNewInstanceCreatesEmptyJournal() {
        TradingJournal j = createJournal();
        TradingJournal newJ = j.newInstance();
        assertEquals(0, newJ.size());
    }

    @Test
    public void testTransferFromMovesTrades() {
        TradingJournal source = createJournal();
        TradingJournal target = createJournal();

        source.addTrade(trade("NQ", 500));
        source.addTrade(trade("ES", -200));

        target.transferFrom(source);

        assertEquals(0, source.size());
        assertEquals(2, target.size());
    }

    @Test
    public void testMultipleAdds() {
        TradingJournal j = createJournal();
        j.addTrade(trade("NQ", 500));
        j.addTrade(trade("ES", -200));
        j.addTrade(trade("YM", 300));
        assertEquals(3, j.size());
    }
}