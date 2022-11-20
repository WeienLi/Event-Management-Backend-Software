package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Workshops extends abstractevent{

    private ArrayList<Workshops> prereq = new ArrayList<>();

    /**
     * private flyweight constructor
     * @param pname name of the Workshops in natural language
     * @param plocation Location of the Workshops in Location enum
     * @param plocaldate date of the Workshops in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this Workshops.
     * @param ptotalticketnum int that shows the number of tickets for this Workshops.
     */
    private Workshops(String pname, Location plocation, LocalDate plocaldate,
                      double ppriceperperson, int ptotalticketnum) {
        super(pname, plocation, plocaldate, ppriceperperson, ptotalticketnum);
    }

    /**
     * private flyweight constructor that construct a coming soon Workshops event
     * @param pname name of the Workshops in natural language
     * @param alocaldate date of the Workshops in localdate datatypes
     */
    private Workshops(String pname, LocalDate alocaldate) {
        super(pname, alocaldate);
    }

    /**
     * Static factory method for coming soon event. It does not requires the flyweight check since location has yet to be
     * set.
     * @param pname pname name of the Workshops in natural language
     * @param alocaldate date of the Workshops in localdate datatypes
     * @return An Workshops event wrapped in optional to match the below method.
     */
    public static Optional<Event> getWorkshop(String pname, LocalDate alocaldate){
        assert pname != null;
        assert alocaldate != null;
        Event c1 = new Workshops(pname,alocaldate);
        abstractevent.addtoAvents(c1);
        return Optional.of(c1);
    }
    /**
     * Static factory method for normal Workshops event. It requries flyweight check
     * @param pname name of the Workshops in natural language
     * @param plocation Location of the Workshops in Location enum
     * @param plocaldate date of the Workshops in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this Workshops.
     * @param ptotalticketnum int that shows the number of tickets for this Workshops.
     * @return An optional event that is present if it passes the flyweight check and the event is created and returned.
     * And is not present otherwise.
     */
    public static Optional<Event> getWorkshop(String pname, Location plocation, LocalDate plocaldate,
                                             double ppriceperperson, int ptotalticketnum){
        assert pname != null;
        assert plocation != null;
        assert plocaldate != null;
        for (Event iter:abstractevent.getAevents()){
            if(iter.getLocation().isPresent()){
                Location loc = iter.getLocation().get();
                if(loc.equals(plocation) && iter.getDate().equals(plocaldate)){
                    return Optional.empty(); //already have same location and time so return an optional empty
                }
            }
            else{
                continue;
            }
        }
        Event s1 = new Workshops(pname,plocation,plocaldate,ppriceperperson,ptotalticketnum);
        abstractevent.addtoAvents(s1);
        return Optional.of(s1);
    }
    /**
     * Override the Abstractevent method to explore polymorphism since for Workshop we do have prerequiste so we do not want to
     * throw an exception instead we should add it to the list
     * @param w1 The workshop that we want to add to the prerequiste list
     */
    @Override
    public void addpreq(Workshops w1){
        this.prereq.add(w1);
    }

    /**
     * The accept Visitor method for visitor design pattern used for visitor
     * @param pVisitor a Vistor type that want to visit this Workshops.
     */
    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitWorkshop(this);
    }
}
