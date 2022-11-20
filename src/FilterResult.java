package src;

import java.util.ArrayList;
import java.util.Iterator;

public class FilterResult implements Visitable{
    private final ArrayList<Event> aFilteredEvents;

    /**
     * Constructor for a filter result
     * @param pFilteredEvents the events that we want to filter or has been filtered
     */
    public FilterResult(ArrayList<Event> pFilteredEvents){
        ArrayList<Event> temp = new ArrayList<>(pFilteredEvents);
        this.aFilteredEvents = temp;
    }

    /**
     * Filtering the events stored with s1's filter strategy
     * @param s1 a filter Strategy for filtering
     * @return a new FilterResult storing the newly filtered
     */
    public FilterResult Filter(FilterStrategy s1){
        ArrayList<Event> temp = new ArrayList<>();
        for (Event e1: aFilteredEvents){
            if(s1.filter(e1)){
                temp.add(e1);
            }
        }
        return new FilterResult(temp);
    }

    /**
     * The accept Visitor method for visitor design pattern
     * @param pVisitor a Vistor type that want to visit this FilterResult.
     */
    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitFilterResult(this);
    }

    /**
     * Get an iterator for the FilteredEvents Field
     * @return iterator for the FilteredEvents Field
     */
    public Iterator<Event> getaFilteredEvents() {
        return aFilteredEvents.iterator();
    }
}
