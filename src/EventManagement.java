package src;

import java.time.LocalDate;
import java.util.*;

//singleton design pattern
/*
Controller to manage events hosted on EventBrite.
 */
public class EventManagement {
    private List<Event> aHostedEvents = new ArrayList<Event>();
    private static EventManagement INSTANCE;

    /**
     * Empty private constructor for singleton
     */
    private EventManagement() {


    }

    /**
     * Factory method for singleton event management
     * @return the event management Instance that was created.
     */
    public static EventManagement getEventManagement(){
        if(INSTANCE == null){
            INSTANCE = new EventManagement();
        }
        return INSTANCE;
    }
    //TO DO: assert for add throws exception for getter setter
    /*
    Method to host a new concert event
     */

    /**
     * Create and add a Normal Concert event to the aHosted event
     * @param pname name of the concert in natural language
     * @param plocation Location of the concert in Location enum
     * @param plocaldate date of the concert in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this concert.
     * @param ptotalticketnum int that shows the number of tickets for this concert.
     * @param pArtist name of the artist in natural language.
     * @throws IllegalArgumentException if the parameters passed in violtes the flyweight design pattern the exception will
     * be thrown
     */
    public void addConcertEvent(String pname, Location plocation, LocalDate plocaldate,
                                double ppriceperperson, int ptotalticketnum, String pArtist) throws IllegalArgumentException{
        Optional<Event> toadd = Concert.getConert(pname,plocation,plocaldate,ppriceperperson,ptotalticketnum,pArtist);
        if(toadd.isPresent()) {
            INSTANCE.aHostedEvents.add(toadd.get());
            return;
        }
        throw new IllegalArgumentException("There is already an event on " + plocaldate + "in " + plocation);
    }

    /**
     * Create and add a Coming Soon Concert event to the aHosted event
     * @param pname pname name of the concert in natural language
     * @param alocaldate date of the concert in localdate datatypes
     */
    // add coming soon concert event
    public void addConcertEvent(String pname, LocalDate alocaldate){
        Optional<Event> toadd = Concert.getConert(pname,alocaldate);
        if(toadd.isPresent()) {
            INSTANCE.aHostedEvents.add(toadd.get());
            return;
        }
        //System.out.println("Creation failed"); //should not happen
    }

    /*
    Method to host a new Gala event
     */

    /**
     * Create and add a Normal Gala event to the aHosted event
     * @param pname name of the Gala in natural language
     * @param plocation Location of the Gala in Location enum
     * @param plocaldate date of the Gala in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this Gala.
     * @param ptotalticketnum int that shows the number of tickets for this Gala.
     * @throws IllegalArgumentException if the parameters passed in violtes the flyweight design pattern the exception will be thrown
     */
    public void addGalaEvent(String pname, Location plocation, LocalDate plocaldate,
                             double ppriceperperson, int ptotalticketnum) throws IllegalArgumentException{
        Optional<Event> toadd = Gala.getGala(pname,plocation,plocaldate,ppriceperperson,ptotalticketnum);
        if(toadd.isPresent()) {
            INSTANCE.aHostedEvents.add(toadd.get());
            return;
        }
        throw new IllegalArgumentException("There is already an event on " + plocaldate + "in " + plocation);
    }

    /**
     * Create and add a Coming Soon Gala event to the aHosted event
     * @param pname name of the Gala in natural language
     * @param alocaldate date of the Gala in localdate datatypes
     */
    public void addGalaEvent(String pname, LocalDate alocaldate){
        Optional<Event> toadd = Gala.getGala(pname,alocaldate);
        if(toadd.isPresent()) {
            INSTANCE.aHostedEvents.add(toadd.get());
            return;
        }
        //System.out.println("Creation failed"); //should not happen
    }
    /*
    Method to host a new Screening event
     */

    /**
     * Create and add a Normal Screening event to the aHosted event
     * @param pname name of the Screening in natural language
     * @param plocation Location of the Screening in Location enum
     * @param plocaldate date of the Screening in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this Screening.
     * @param ptotalticketnum int that shows the number of tickets for this Screening.
     * @param prating Rating of the Screening in Rating enum
     * @throws IllegalArgumentException if the parameters passed in violtes the flyweight design pattern the exception will be thrown
     */
    public void addScreeningEvent(String pname, Location plocation, LocalDate plocaldate,
                                  double ppriceperperson, int ptotalticketnum, Rating prating) throws IllegalArgumentException{
        Optional<Event> toadd = Screening.getScreening(pname,plocation,plocaldate,ppriceperperson,ptotalticketnum,prating);
        if(toadd.isPresent()) {
            INSTANCE.aHostedEvents.add(toadd.get());
            return;
        }
        throw new IllegalArgumentException("There is already an event on " + plocaldate + "in " + plocation);
    }

    /**
     * Create and add a Coming Soon Screening event to the aHosted event
     * @param pname name of the Screening in natural language
     * @param alocaldate date of the Screening in localdate datatypes
     */
    public void addScreeningEvent (String pname, LocalDate alocaldate){
        Optional<Event> toadd = Screening.getScreening(pname,alocaldate);
        if(toadd.isPresent()) {
            INSTANCE.aHostedEvents.add(toadd.get());
            return;
        }
        //System.out.println("Creation failed"); //should not happen
    }

    /*
    Method to host a new Workshop event
     */
    /**
     * Create and add a Normal Workshop event to the aHosted event
     * @param pname name of the Workshop in natural language
     * @param plocation Location of the Workshop in Location enum
     * @param plocaldate date of the Workshop in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this Workshop.
     * @param ptotalticketnum int that shows the number of tickets for this Workshop.
     * @throws IllegalArgumentException if the parameters passed in violtes the flyweight design pattern the exception will be thrown
     */
    public void addWorkshopEvent(String pname, Location plocation, LocalDate plocaldate,
                                 double ppriceperperson, int ptotalticketnum) throws IllegalArgumentException{
        Optional<Event> toadd = Workshops.getWorkshop(pname,plocation,plocaldate,ppriceperperson,ptotalticketnum);
        if(toadd.isPresent()) {
            INSTANCE.aHostedEvents.add(toadd.get());
            return;
        }
        throw new IllegalArgumentException("There is already an event on " + plocaldate + "in " + plocation);
    }

    /**
     * Create and add a Coming Soon Workshop event to the aHosted event
     * @param pname name of the Workshop in natural language
     * @param alocaldate date of the Workshop in localdate datatypes
     */
    public void addWorkshopEvent(String pname, LocalDate alocaldate){
        Optional<Event> toadd = Workshops.getWorkshop(pname,alocaldate);
        if(toadd.isPresent()) {
            INSTANCE.aHostedEvents.add(toadd.get());
            return;
        }
        //System.out.println("Creation failed"); //should not happen
    }

    /**
     * Create and add a Festival event to the aHosted event
     * @param pevents The events of the Festival
     * @param pname The name of the festival
     * @throws IllegalArgumentException if the parameters passed in violtes the flyweight design pattern the exception will be thrown
     */
    public void addFestival(ArrayList<Event> pevents,String pname) throws IllegalArgumentException{
        Optional<Event> toadd = Festival.getFestival(pevents,pname);
        if(toadd.isPresent()) {
            INSTANCE.aHostedEvents.add(toadd.get());
            return;
        }
        throw new IllegalArgumentException("Can not create such festival");
    }
    //ensure visitor is not repeated used so we create it for the user

    /**
     * Calculate the profit of all the events after filtering using filter and times to corresponding multiplier
     * @param filter Filter Strategy that we used to filter out the event
     * @param concertper The multiplier for concert event
     * @param galaper The multiplier for gala event
     * @param workshopper The multiplier for workshop event
     * @param screeningper The multiplier for screening event
     * @return The double representing the profit of all events after filtering and using multiplier
     */
    public double profitcalculator(FilterStrategy filter, double concertper,double galaper,double workshopper,double screeningper){
        assert filter != null;
        assert concertper <= 1 && concertper >= 0 ;
        assert galaper <= 1 && galaper >= 0 ;
        assert workshopper <= 1 && workshopper >= 0 ;
        assert screeningper <= 1 && screeningper >= 0 ;
        //not allowing negative profit
        FilterResult fr = new FilterResult((ArrayList<Event>) this.aHostedEvents);
        FilterResult result = fr.Filter(filter);
        ProfitVisitor v1 = new ProfitVisitor(concertper, galaper, workshopper, screeningper);
        result.accept(v1);
        return v1.getProfit();
    }

    /**
     * Count the number of vips after filtering using filter
     * @param filter Filter Strategy that we used to filter out the event
     * @return int representing the number of vips
     */
    //ensure visitor is not repeated used so we create it for the user
    public int vipcalculator(FilterStrategy filter){
        assert filter != null;
        FilterResult fr = new FilterResult((ArrayList<Event>) this.aHostedEvents);
        FilterResult result = fr.Filter(filter);
        Vipvisitor v1 = new Vipvisitor();
        result.accept(v1);
        return v1.getNumofvips();
    }

    /**
     * Use location and localdate as unique identifier by event's flyweight definition to get the Name of the event held
     * at plocation at plocaldate
     * @param plocation Location of the Event in Location enum
     * @param plocaldate Localdate of the event in localdate datatype
     * @return String representing the natural langauge of the event's name
     * @throws IllegalArgumentException If there is no event held at location and localdate pair.
     */
    //use location and date as unique identifier
    public String getEventName(Location plocation, LocalDate plocaldate) throws IllegalArgumentException{
        assert plocaldate != null && plocation != null;
        for(Event iter: aHostedEvents){
            if(iter.getLocation().isPresent()){
                if(iter.getLocation().get().equals(plocation) && plocaldate.equals(iter.getDate())){
                    return iter.getName();
                }
            }
        }
        throw new IllegalArgumentException("No event found !");
    }
    /**
     * Use location and localdate as unique identifier by event's flyweight definition to get the price of the event held
     * at plocation at plocaldate
     * @param plocation Location of the Event in Location enum
     * @param plocaldate Localdate of the event in localdate datatype
     * @return double representing the price per ticket
     * @throws IllegalArgumentException If there is no event held at location and localdate pair.
     * @throws NoSuchElementException if the event held at plocation at plocaldate has yet to have it's price set.
     */
    public double getprice(Location plocation, LocalDate plocaldate) throws NoSuchElementException,IllegalArgumentException{
        assert plocaldate != null && plocation != null;
        for(Event iter: aHostedEvents){
            if(iter.getLocation().isPresent()){
                if(iter.getLocation().get().equals(plocation) && plocaldate.equals(iter.getDate())){
                    Optional<Double> price = iter.getPrice();
                    if(price.isPresent()){
                        return price.get();
                    }
                    else{
                        throw new NoSuchElementException("Price for this event has yet to be set");
                    }
                }
            }
        }
        throw new IllegalArgumentException("No event found !");
    }

    /**
     * Use location and localdate as unique identifier by event's flyweight definition to get the num of tickets of the event held
     * at plocation at plocaldate
     * @param plocation Location of the Event in Location enum
     * @param plocaldate Localdate of the event in localdate datatype
     * @return int representing the number of tickets
     * @throws NoSuchElementException if the event held at plocation at plocaldate has yet to have it's number of tickets set.
     * @throws IllegalArgumentException If there is no event held at location and localdate pair.
     */
    //use location and date as unique identifier
    public int getnumtickets(Location plocation, LocalDate plocaldate) throws NoSuchElementException, IllegalArgumentException{
        assert plocaldate != null && plocation != null;
        for(Event iter: aHostedEvents){
            if(iter.getLocation().isPresent()){
                if(iter.getLocation().get().equals(plocation) && plocaldate.equals(iter.getDate())){
                    Optional<Integer> numtickets = iter.getNumTickets();
                    if(numtickets.isPresent()){
                        return numtickets.get();
                    }
                    else{
                        throw new NoSuchElementException("number of tickets for this event has yet to be set");
                    }
                }
            }
        }
        throw new IllegalArgumentException("No event found !");
    }

    //since we are getting unique identifier so we use index

    /**
     * get the location of the event at index in ahostedevent
     * @param index int representing the index of event in ahostedevents
     * @return Location representing location of the event hold at index
     * @throws ArrayIndexOutOfBoundsException thrown if index is out of bound
     * @throws NoSuchElementException thrown if location has yet to be set for this
     */
    public Location getLocation(int index) throws ArrayIndexOutOfBoundsException,NoSuchElementException{
        if(index < 0 || index >= aHostedEvents.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        Event e1 = aHostedEvents.get(index);
        if(e1.getLocation().isPresent()){
            return e1.getLocation().get();
        }
        throw new NoSuchElementException("Location for this event has yet to be set");
    }

    /**
     * Get the local date of the event at index
     * @param index int representing the index of event in ahosted event
     * @return The date of the event at index
     * @throws ArrayIndexOutOfBoundsException thrown if index out of bound
     */
    public LocalDate getDate(int index) throws ArrayIndexOutOfBoundsException{
        if(index < 0 || index >= aHostedEvents.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return aHostedEvents.get(index).getDate();
    }

    /**
     * Set the location of the event at index
     * @param index int representing the index of event in ahosted event
     * @param loc The location we want to set this event to
     * @throws ArrayIndexOutOfBoundsException thrown if index out of bound
     */
    public void setLocation(int index,Location loc) throws ArrayIndexOutOfBoundsException{
        assert loc != null;
        if(index < 0 || index >= aHostedEvents.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        aHostedEvents.get(index).setAlocation(loc);
    }

    /**
     * Set the price of ticket for the event hold at plcation at plocaldate used as unique identifier
     * @param plocation Location of the event
     * @param plocaldate LocalDate of the event
     * @param apriceperperson the price we want to set this event to
     * @throws IllegalArgumentException if the event was not found at plocation and plocaldate pair
     */
    public void setApriceperperson(Location plocation, LocalDate plocaldate,
                                   double apriceperperson) throws IllegalArgumentException {
        assert plocaldate != null && plocation != null;
        for (Event iter : aHostedEvents) {
            if (iter.getLocation().isPresent()) {
                if (iter.getLocation().get().equals(plocation) && plocaldate.equals(iter.getDate())) {
                    iter.setApriceperperson(apriceperperson);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("No event found !");
    }
    /**
     * Set the number of ticket for the event hold at plcation at plocaldate used as unique identifier
     * @param plocation Location of the event
     * @param plocaldate LocalDate of the event
     * @param atotalticketnum int the number of tickets  we want to set this event to
     * @throws IllegalArgumentException if the event was not found at plocation and plocaldate pair
     */
    public void setAtotalticketnum(Location plocation, LocalDate plocaldate,
                                   int atotalticketnum) throws IllegalArgumentException{
        assert plocaldate != null && plocation != null;
        for (Event iter : aHostedEvents) {
            if (iter.getLocation().isPresent()) {
                if (iter.getLocation().get().equals(plocation) && plocaldate.equals(iter.getDate())) {
                    iter.setAtotalticketnum(atotalticketnum);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("No event found !");
    }


    /**
     * addprequiste for a Workshop event if it is not a workshop event exception are thrown
     * @param plocation the location of the prerequiste that we want to add
     * @param plocaldate the localdate of the prerequiste that we want to add (plocaldate and plocation form unique identifier)
     * @param indexaddto the index that held the event within aHostedevent that we want to add a prerequsite to
     * @throws ArrayIndexOutOfBoundsException
     * @throws IllegalArgumentException
     * @throws UnsupportedOperationException
     * @throws ClassCastException
     */
    public void addpreq(Location plocation, LocalDate plocaldate, int indexaddto)
            throws ArrayIndexOutOfBoundsException,IllegalArgumentException,UnsupportedOperationException,ClassCastException{
        assert plocaldate != null && plocation != null;
        if(indexaddto < 0 || indexaddto >= aHostedEvents.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        Event e1 = aHostedEvents.get(indexaddto);
        for (Event iter : aHostedEvents) {
            if (iter.getLocation().isPresent()) {
                if (iter.getLocation().get().equals(plocation) && plocaldate.equals(iter.getDate())) {
                    if(e1.equals(iter)){
                        throw new IllegalArgumentException("Not allowed to add itself to it's prerequisite");
                    }
                    e1.addpreq((Workshops) iter);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("prerequiste not found");
    }

    /**
     * addvips for a Gala/Concert event if it not those type exception are thrown
     * @param plocation the location of the prerequiste that we want to add
     * @param plocaldate the localdate of the prerequiste that we want to add (plocaldate and plocation form unique identifier)
     * @param v1 the vip that we would like to add to this event
     * @throws UnsupportedOperationException thrown if type is not Gala/Concert
     * @throws IllegalArgumentException thrown if no event found in plocaldate plocation pair
     */
    public void addvips(Location plocation, LocalDate plocaldate,VIPS v1) throws UnsupportedOperationException,
            IllegalArgumentException {
        assert plocaldate != null && plocation != null && v1 !=null;
        for (Event iter : aHostedEvents) {
            if (iter.getLocation().isPresent()) {
                if (iter.getLocation().get().equals(plocation) && plocaldate.equals(iter.getDate())) {
                    iter.addvips(v1);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Event not found");
    }

    /**
     * set the partist for a Concert event if it not a Concert type exception are thrown
     * @param plocation the location of the prerequiste that we want to add
     * @param plocaldate the localdate of the prerequiste that we want to add (plocaldate and plocation form unique identifier)
     * @param pArtist Natural language String that we would like to set this event's artist to
     * @throws UnsupportedOperationException thrown if type is not Concert
     * @throws IllegalArgumentException thrown if no event found in plocaldate plocation pair
     */
    public void setpArtist(Location plocation, LocalDate plocaldate,String pArtist) throws UnsupportedOperationException,
            IllegalArgumentException{
        assert plocaldate != null && plocation != null && pArtist !=null;
        for (Event iter : aHostedEvents) {
            if (iter.getLocation().isPresent()) {
                if (iter.getLocation().get().equals(plocation) && plocaldate.equals(iter.getDate())) {
                    iter.setpArtist(pArtist);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Event not found");
    }

    /**
     * set the arating for a Screening event if it not a Screen type exception are thrown
     * @param plocation the location of the prerequiste that we want to add
     * @param plocaldate the localdate of the prerequiste that we want to add (plocaldate and plocation form unique identifier)
     * @param arating The rating we would like to set for this event
     * @throws UnsupportedOperationException thrown if type is not Workshop
     * @throws IllegalArgumentException thrown if not event found in plocaldate plocation pair
     */
    public void setArating(Location plocation, LocalDate plocaldate, Rating arating) throws UnsupportedOperationException,
            IllegalArgumentException{
        assert plocaldate != null && plocation != null && arating !=null;
        for (Event iter : aHostedEvents) {
            if (iter.getLocation().isPresent()) {
                if (iter.getLocation().get().equals(plocation) && plocaldate.equals(iter.getDate())) {
                    iter.setArating(arating);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Event not found");
    }

    /*
    Return the list of hosted events on EventBrite.
    This method assumes that Events are immutable.
     */

    /**
     * Get a copy of ahosted event Arraylist
     * @return an arraylist of events stored in this event management.
     */
    public ArrayList<Event> getHostedEvents(){
        return new ArrayList<Event>(aHostedEvents);
    }
}
