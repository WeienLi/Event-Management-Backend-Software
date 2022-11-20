package src;


import java.util.Iterator;

public class ProfitVisitor implements Visitor{
    private double profit;
    private double concertper;
    private double galaper;
    private double workshopper;
    private double screeningper;

    /**
     * Constructor of a profit visitor
     * @param concertper Concert's multiplier
     * @param galaper Gala's multiplier
     * @param workshopper Workshop's multiplier
     * @param screeningper Screening's multiplier
     */
    public ProfitVisitor(double concertper,double galaper,double workshopper,double screeningper) {
        assert concertper <= 1 && concertper >= 0 ;
        assert galaper <= 1 && galaper >= 0 ;
        assert workshopper <= 1 && workshopper >= 0 ;
        assert screeningper <= 1 && screeningper >= 0 ;
        this.profit = 0;
        this.concertper = concertper;
        this.galaper = galaper;
        this.workshopper = workshopper;
        this.screeningper = screeningper;
    }

    /**
     * Visit a Filter Result by iterating through all it's event stored
     * @param pfilter The Filter Result to be visited
     */
    @Override
    public void visitFilterResult(FilterResult pfilter) {
        for(Iterator<Event> i = pfilter.getaFilteredEvents(); i.hasNext(); )
        {
            i.next().accept(this);
        }
    }

    /**
     * Visit a Concert  and calculate it's profit using multiplier and number of tickets and price per ticket of that concert
     * @param pconcert Concert to be visited
     */
    @Override
    public void visitConcert(Concert pconcert) {
        if(pconcert.getNumTickets().isPresent() && pconcert.getPrice().isPresent()){
            profit += (this.concertper * pconcert.getPrice().get() * pconcert.getNumTickets().get());
        }
    }

    /**
     * Visit a Gala and calculate it's profit using multiplier and number of tickets and price per ticket of that Gala
     * @param pgala Gala to be visited
     */
    @Override
    public void visitGala(Gala pgala) {
        if(pgala.getNumTickets().isPresent() && pgala.getPrice().isPresent()){
            profit += (this.galaper * pgala.getPrice().get() * pgala.getNumTickets().get());
        }
    }

    /**
     * Visit a Workshops and calculate it's profit using multiplier and number of tickets and price per ticket of that Workshops
     * @param pwork Workshop to be visited
     */
    @Override
    public void visitWorkshop(Workshops pwork) {
        if(pwork.getNumTickets().isPresent() && pwork.getPrice().isPresent()){
            profit += (this.workshopper * pwork.getPrice().get() * pwork.getNumTickets().get());
        }
    }

    /**
     * Visit a Screening and calculate it's profit using multiplier and number of tickets and price per ticket of that Screening
     * @param pscreen Screening to be visited
     */
    @Override
    public void visitScreening(Screening pscreen) {
        if(pscreen.getNumTickets().isPresent() && pscreen.getPrice().isPresent()){
            profit += (this.screeningper * pscreen.getPrice().get() * pscreen.getNumTickets().get());
        }
    }

    /**
     * Visit a Festival and make all the events with in the festival to accept this visitor
     * @param pfest Festival to be visited
     */
    @Override
    public void visitFestival(Festival pfest) {
        for(Event p:pfest.getEvents()){
            p.accept(this);
        }
    }

    /**
     * get the profit
     * @return double representing the profit
     */
    public double getProfit() {
        return profit;
    }
}
