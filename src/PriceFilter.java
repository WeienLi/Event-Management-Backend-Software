package src;

import java.util.Optional;

public class PriceFilter implements FilterStrategy{
    private final double lower;
    private final double upper;

    /**
     * Constructor of a price filter
     * @param lower lowerbound of the filter
     * @param upper upper bound of the filter
     */
    public PriceFilter(double lower, double upper) {
        assert lower < upper;
        this.lower = lower;
        this.upper = upper;
    }

    /**
     * Override the interface method that tells you whether the event satisfies the filter's requriements or not
     * @param pevent the event we want to check that it satisfies the conditions or not
     * @return boolean that tells you whether pevent satisfies this filter
     */
    @Override
    public boolean filter(Event pevent) {
        Optional<Double> eprice = pevent.getPrice();
        if(eprice.isPresent()){
            return lower <= eprice.get() && eprice.get() <= upper;
        }
        return false;
    }
}
