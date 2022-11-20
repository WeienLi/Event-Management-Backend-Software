package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Festival extends abstractevent{ //extends abstractevent
    private final ArrayList<Event> events;

    /**
     * private flyweight constructor that construct a Festival event
     * @param pname name of the Festival in natural language
     * @param plocation Location of the Festval in Location enum
     * @param plocaldate date of the Festval in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this Festval.
     * @param ptotalticketnum int that shows the number of tickets for this Festval.
     * @param pevents An arraylist of event that is in this festival.
     */
    private Festival(ArrayList<Event> pevents,String pname, Location plocation, LocalDate plocaldate,
                     double ppriceperperson, int ptotalticketnum) {
        super(pname, plocation, plocaldate, ppriceperperson, ptotalticketnum);
        this.events = pevents;
    }
    //On ed the TA said we can assume there is at least one event that is not coming soon within the festival list
    //thus no coming soon festival

    /**
     * Static factory method to create a Festival. Festival's ticket price would be 0.2 * sum of all price, ticketnumber
     * would be the event's ticket number with the least ticket number. plocation would be at a location if all events
     * are held at that location multiple otherwise, and date is the first event's date
     * @param pevents An arraylist of events that is within the Festival
     * @param pname Natural language name for the festival
     * @return an optional Festival event that is present if it does not violate flyweight design pattern empty otherwise
     */
    public static Optional<Event> getFestival(ArrayList<Event> pevents,String pname){
        assert pname != null;
        assert pevents != null;
        double totalprice = 0;
        int numtickets = Integer.MAX_VALUE;
        Optional<Location> loc = Optional.empty();
        LocalDate firstdate = LocalDate.MAX;
        for(Event iter: pevents){
            if(!loc.isPresent()){
                if(iter.getLocation().isPresent()){
                    loc = iter.getLocation();
                }
            }
            else{
                if(iter.getLocation().isPresent()){
                    if(!iter.getLocation().equals(loc)){
                        loc = Optional.of(Location.Multiple);
                    }
                }
            }
            if(iter.getPrice().isPresent()){
                totalprice += iter.getPrice().get();
            }
            if(iter.getNumTickets().isPresent()){
                if(numtickets > iter.getNumTickets().get()){
                    numtickets = iter.getNumTickets().get();
                }
            }
            if(firstdate.compareTo(iter.getDate()) > 0){
                firstdate = iter.getDate();
            }
        }
        for (Event iter1:abstractevent.getAevents()){
            if(iter1.getLocation().isPresent()){
                Optional<Location> loc1 = iter1.getLocation();
                if(loc1.equals(loc) && iter1.getDate().equals(firstdate)){
                    return Optional.empty();
                }
            }
        }
        Event f1 = new Festival(pevents,pname,loc.get(),firstdate,totalprice * 0.2,numtickets);
        abstractevent.addtoAvents(f1);
        return Optional.of(f1);
    }

    /**
     * Get an arraylist of events used in visitor and test
     * @return return a copy of ahostedevents
     */
    public ArrayList<Event> getEvents() {
        return new ArrayList<>(this.events);
    }

    /**
     * The accept Visitor method for visitor design pattern
     * @param pVisitor a Vistor type that want to visit this Festival.
     */
    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitFestival(this);
    }
}
