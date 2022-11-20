package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Gala extends abstractevent{
    private final ArrayList<VIPS> vips = new ArrayList<>();

    /**
     * private flyweight constructor
     * @param pname name of the Gala in natural language
     * @param plocation Location of the Gala in Location enum
     * @param plocaldate date of the Gala in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this Gala.
     * @param ptotalticketnum int that shows the number of tickets for this Gala.
     */
    private Gala(String pname, Location plocation, LocalDate plocaldate,
                    double ppriceperperson, int ptotalticketnum) {
        super(pname, plocation, plocaldate, ppriceperperson, ptotalticketnum);
    }

    /**
     * private flyweight constructor that construct a coming soon Gala event
     * @param pname name of the Gala in natural language
     * @param alocaldate date of the Gala in localdate datatypes
     */
    private Gala(String pname, LocalDate alocaldate) {
        super(pname, alocaldate);
    }

    /**
     * Static factory method for coming soon event. It does not requires the flyweight check since location has yet to be
     * set.
     * @param pname pname name of the Gala in natural language
     * @param alocaldate date of the Gala in localdate datatypes
     * @return An Gala event wrapped in optional to match the below method.
     */
    //Coming soon
    public static Optional<Event> getGala(String pname, LocalDate alocaldate){
        assert pname != null;
        assert alocaldate != null;
        Event c1 = new Gala(pname,alocaldate);
        abstractevent.addtoAvents(c1);
        return Optional.of(c1);
    }

    /**
     * Static factory method for normal Gala event. It requries flyweight check
     * @param pname name of the Gala in natural language
     * @param plocation Location of the Gala in Location enum
     * @param plocaldate date of the Gala in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this Gala.
     * @param ptotalticketnum int that shows the number of tickets for this Gala.
     * @return An optional event that is present if it passes the flyweight check and the event is created and returned.
     * And is not present otherwise.
     */
    //need to deal with location optional
    public static Optional<Event> getGala(String pname, Location plocation, LocalDate plocaldate,
                                            double ppriceperperson, int ptotalticketnum){
        assert pname != null;
        assert plocation != null;
        assert plocaldate != null;
        for (Event iter:abstractevent.getAevents()){
            if(iter.getLocation().isPresent()){
                Location loc = iter.getLocation().get();

                if(loc.equals(plocation) && iter.getDate().equals(plocaldate)){
                    return Optional.empty();
                }
            }
            else{
                continue;
            }
        }
        Event c1 = new Gala(pname,plocation,plocaldate,ppriceperperson,ptotalticketnum);
        abstractevent.addtoAvents(c1);
        return Optional.of(c1);
    }

    /**
     * Override the Abstractevent method to explore polymorphism since for Gala we do have vips so we do not want to
     * throw an exception instead we should add it to the list
     * @param v1 The vip that we want to add to this's vip list
     */
    @Override
    public void addvips(VIPS v1){
        // no need of deepcopy vips having nothing
        vips.add(v1);
    }
    /**
     * Get the vip Arraylist no need to deepcopy since vip is an empty class used for visitor and test
     * @return a copy of the vips arraylist
     */
    public ArrayList<VIPS> getVips() {
        return new ArrayList<VIPS>(vips);
    }

    /**
     * The accept Visitor method for visitor design pattern used for visitor
     * @param pVisitor a Vistor type that want to visit this Gala.
     */
    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitGala(this);
    }
}
