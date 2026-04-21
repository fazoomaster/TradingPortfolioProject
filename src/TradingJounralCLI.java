public final class TradingJournalCLI {

    private TradingJournalCLI() {
    }

    public static void main(String[] args) {

        TradingJournal journal = new TradingJournal1();

        journal.addTrade(new TradingJournal1.Trade1("NQ", 500));
        journal.addTrade(new TradingJournal1.Trade1("ES", -200));
        journal.addTrade(new TradingJournal1.Trade1("YM", 300));

        System.out.println("Number of trades: " + journal.size());
        System.out.println("Total P/L: " + journal.getTotalProfitLoss());
        System.out.println("Win %: " + journal.getWinPercentage());
    }
}