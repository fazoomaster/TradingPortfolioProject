import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Kernel implementation of TradingJournal component.
 */
public class TradingJournal1 extends TradingJournalSecondary {

    /*
     * Private representation
     */
    private Sequence<Trade> trades;

    /*
     * Representation Invariant: trades is not null
     *
     * Correspondence: this = sequence of trades stored in trades
     */

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.trades = new Sequence1L<>();
    }

    /**
     * No-argument constructor.
     */
    public TradingJournal1() {
        this.createNewRep();
    }

    /*
     * Standard methods
     */

    @Override
    public final TradingJournal newInstance() {
        return new TradingJournal1();
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(TradingJournal source) {
        TradingJournal1 localSource = (TradingJournal1) source;
        this.trades = localSource.trades;
        localSource.createNewRep();
    }

    /*
     * Kernel methods
     */

    @Override
    public final void addTrade(Trade trade) {
        this.trades.add(this.trades.length(), trade);
    }

    @Override
    public final Trade removeLastTrade() {
        return this.trades.remove(this.trades.length() - 1);
    }

    @Override
    public final int size() {
        return this.trades.length();
    }
}
