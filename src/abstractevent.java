package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

/**
 * An abstract event for inheirtance
 * Flyweight design pattern
 */

public abstract class abstractevent implements Event{
    private final String aname;
    private Optional<Location> alocation;
    private final LocalDate alocaldate;
    private Optional<Double> apriceperperson;
    private Optional<Integer> atotalticketnum;
    private static final ArrayList<Event> aevents = new ArrayList<>();

    /**
     * Constructor for normal event where everything about an event is known
     * @param pname The name of the event in natural language
     * @param plocation The location of the event from the location enum
     * @param plocaldate The date of the event in Localdate datatype
     * @param ppriceperperson double representing the price each person
     * @param ptotalticketnum int representing the number of tickets the event have
     */

    public abstractevent(String pname, Location plocation, LocalDate plocaldate, double ppriceperperson,
                         int ptotalticketnum) {
        assert pname != null;
        assert plocation != null;
        assert plocaldate != null;
        this.aname = pname;
        this.alocation = Optional.of(plocation);
        this.alocaldate = plocaldate;
        this.apriceperperson = Optional.of(ppriceperperson);
        this.atotalticketnum = Optional.of(ptotalticketnum);
    }
    //coming soon

    /**
     * The constructor for coming soon event
     * @param pname The name of the event in natural language
     * @param alocaldate The date of the event in Localdate datatype
     */
    public abstractevent(String pname, LocalDate alocaldate){
        assert pname != null;
        assert alocaldate != null;
        this.aname = pname;
        this.alocaldate = alocaldate;
        this.alocation = Optional.empty();
        this.apriceperperson = Optional.empty();
        this.atotalticketnum = Optional.empty();
    }

    /**
     * Override method of the interface get the name of the event
     * @return natural language name stored
     */
    @Override
    public String getName() {
        return this.aname;
    }

    /**
     * Override method of the interface that get the location of event
     * @return An optional of location since location may be absent as if right now
     */
    @Override
    public Optional<Location> getLocation() {
        return this.alocation;
    }
    //Localdate in immutable through research can directly return

    /**
     * Override method of the interface that get the date of the event
     * @return the local date stored for this event can directly return since local date is immutable
     */
    @Override
    public LocalDate getDate() {
        return this.alocaldate;
    }

    /**
     * Override method of the interface that get the price per ticket of the event
     * @return the price per ticket of the event wrapped in optional since may be absent as if right now
     */
    @Override
    public Optional<Double> getPrice() {
        return this.apriceperperson;
    }

    /**
     * Override method of the interface that get the number of tickets for this event
     * @return the number of tickets for this event wrapped in optional since may be absent as if right now
     */
    @Override
    public Optional<Integer> getNumTickets() {
        return this.atotalticketnum;
    }

    /**
     * Getter for the Arraylist storage of the flyweight Events created. Used for implementing flyweight and Unit test
     * @return a copy of such arraylist
     */
    public static ArrayList<Event> getAevents() {
        return new ArrayList<Event>(abstractevent.aevents);
    }

    /**
     * Add an event to the Flyweight storage
     * @param pevent the event we want to add to the storage
     */
    public static void addtoAvents(Event pevent){
        aevents.add(pevent);
    }

    /**
     * Override the method in interface that set the location of this event without breaking the flyweight design pattern
     * @param alocation The location that we want to set this event to
     * @throws IllegalArgumentException If the alocation provided breaks the flyweight design pattern throw this exception
     */
    @Override
    public void setAlocation(Location alocation) throws IllegalArgumentException {
        for (Event iter:abstractevent.getAevents()){
            if(iter.getLocation().isPresent()){
                Location loc = iter.getLocation().get();
                if(loc.equals(alocation) && iter.getDate().equals(this.getDate())){
                    throw new IllegalArgumentException("This location already have an event held");
                }
            }
        }
        this.alocation = Optional.of(alocation);
    }

    /**
     * Override interface method that set the price per ticket of this event
     * @param apriceperperson double the new price we want to set this event to.
     */
    @Override
    public void setApriceperperson(double apriceperperson) {
        this.apriceperperson = Optional.of(apriceperperson);
    }

    /**
     * Override interface method that set the total amount of ticket number of this event
     * @param atotalticketnum int the new number of tickets we want to set this event to.
     */
    @Override
    public void setAtotalticketnum(int atotalticketnum) {
        this.atotalticketnum = Optional.of(atotalticketnum);
    }

    /**
     * Override method of the interface, if this method is called on an event that is not type workshop we would throw
     * an exception and workshop will override this method to explore polymorphism.
     * @param w1 the workshop that we would like to add as this's prerequiste
     * @throws UnsupportedOperationException Thrown when object called on is not type workshop
     */
    @Override
    public void addpreq(Workshops w1) throws UnsupportedOperationException{
        throw new UnsupportedOperationException ("This object is not Type Workshop");
    }

    /**
     * Override method of the interface, if this method is called on an event that is not type Screening we would throw
     * an exception and screening will override this method to explore polymorphism.
     * @param arating the rating that we want to set this's ratings to.
     * @throws UnsupportedOperationException Thrown when object called on is not type screening
     */
    @Override
    public void setArating(Rating arating) throws UnsupportedOperationException{
        throw new UnsupportedOperationException ("This object is not Type Screening");
    }

    /**
     * Override method of the interface, if this method is called on an event that is not type Concert we would throw
     * an exception and concert will override this method to explore polymorphism.
     * @param pArtist the Artist that we want to set this's artist to.
     * @throws UnsupportedOperationException Thrown when object called is not an concert
     */
    @Override
    public void setpArtist(String pArtist) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("This object is not Type Concert");
    }

    /**
     * Override method of the interface, if this method is called on an event that is not type Concert or Gala we would
     * throw an exception and concert and Gala will override this method to explore polymorphism.
     * @param v1 The vip that we want to add to this's vip list
     * @throws UnsupportedOperationException Thrown when object called is not an concert or Gala
     */
    @Override
    public void addvips(VIPS v1) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("This object is not Type Concert or Type Gala");
    }
}
