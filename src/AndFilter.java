package src;

public class AndFilter implements FilterStrategy{
    // can only combine 2 at a time to simplfy code
    private final FilterStrategy filter1;
    private final FilterStrategy filter2;

    /**
     * Constructor construct a combined filter of two filters connected with and.
     * @param filter1 one of the filter Strategy
     * @param filter2 the other filter Strategy
     */
    public AndFilter(FilterStrategy filter1, FilterStrategy filter2) {
        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    /**
     * Override the interface method that tells you whether the event satisfies the filter's requriements or not
     * @param pevent the event we want to check that it satisfies the conditions or not
     * @return boolean that tells you whether pevent satisfies this filter
     */
    @Override
    public boolean filter(Event pevent) {
        return filter1.filter(pevent) && filter2.filter(pevent);
    }
}
