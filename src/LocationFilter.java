package src;

import java.util.Optional;

public class LocationFilter implements FilterStrategy{
    private final Location tocheck;

    /**
     * Constructor of a location Filter
     * @param tocheck the location that we want to check whether those event has is in those location
     */
    public LocationFilter(Location tocheck) {
        this.tocheck = tocheck;
    }

    /**
     * Override the interface method that tells you whether the event satisfies the filter's requriements or not
     * @param pevent the event we want to check that it satisfies the conditions or not
     * @return boolean that tells you whether pevent satisfies this filter
     */
    @Override
    public boolean filter(Event pevent) {
        Optional<Location> l1= pevent.getLocation();
        if(l1.isPresent()){
            return l1.get().equals(this.tocheck);
        }
        return false;
    }
}
