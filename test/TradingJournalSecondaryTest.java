public class TradingJournalSecondaryTest {

    private TradingJournal createJournal() {
        return new TradingJournal1();
    }

    private TradingJournalKernel.Trade trade(String symbol, double pnl) {
        return new TradingJournal1.Trade1(symbol, pnl);
    }

    @Test
    public void testTotalProfitLossMixedTrades() {
        TradingJournal j = createJournal();
        j.addTrade(trade("NQ", 500));
        j.addTrade(trade("ES", -200));
        j.addTrade(trade("YM", 300));

        assertEquals(600, j.getTotalProfitLoss(), 0.001);
    }

    @Test
    public void testTotalProfitLossAllLosses() {
        TradingJournal j = createJournal();
        j.addTrade(trade("NQ", -100));
        j.addTrade(trade("ES", -200));

        assertEquals(-300, j.getTotalProfitLoss(), 0.001);
    }

    @Test
    public void testWinPercentageAllWins() {
        TradingJournal j = createJournal();
        j.addTrade(trade("NQ", 100));
        j.addTrade(trade("ES", 200));

        assertEquals(100.0, j.getWinPercentage(), 0.001);
    }

    @Test
    public void testWinPercentageAllLosses() {
        TradingJournal j = createJournal();
        j.addTrade(trade("NQ", -100));
        j.addTrade(trade("ES", -200));

        assertEquals(0.0, j.getWinPercentage(), 0.001);
    }

    @Test
    public void testWinPercentageMixed() {
        TradingJournal j = createJournal();
        j.addTrade(trade("NQ", 100));
        j.addTrade(trade("ES", -100));
        j.addTrade(trade("YM", 200));

        assertEquals(66.666, j.getWinPercentage(), 0.01);
    }

    @Test
    public void testSecondaryMethodsDoNotModifyJournal() {
        TradingJournal j = createJournal();
        j.addTrade(trade("NQ", 100));
        j.addTrade(trade("ES", -100));

        int originalSize = j.size();

        j.getTotalProfitLoss();
        j.getWinPercentage();

        assertEquals(originalSize, j.size());
    }
}