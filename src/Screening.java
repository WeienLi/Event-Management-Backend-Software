package src;

import java.time.LocalDate;
import java.util.Optional;

public class Screening extends abstractevent{
    private Optional<Rating> arating;

    /**
     * private flyweight constructor
     * @param pname name of the Screening in natural language
     * @param plocation Location of the Screening in Location enum
     * @param plocaldate date of the Screening in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this Screening.
     * @param ptotalticketnum int that shows the number of tickets for this Screening.
     * @param arating Rating of the Screening in Rating enum
     */
    private Screening(String pname, Location plocation, LocalDate plocaldate,
                 double ppriceperperson, int ptotalticketnum, Rating arating) {
        super(pname, plocation, plocaldate, ppriceperperson, ptotalticketnum);
        this.arating = Optional.of(arating);
    }

    /**
     * private flyweight constructor that construct a coming soon Screening event
     * @param pname name of the Screening in natural language
     * @param alocaldate date of the Screening in localdate datatypes
     */
    private Screening(String pname, LocalDate alocaldate) {
        super(pname, alocaldate);
        this.arating = Optional.empty();
    }

    /**
     * Static factory method for coming soon event. It does not requires the flyweight check since location has yet to be
     * set.
     * @param pname pname name of the Screening in natural language
     * @param alocaldate date of the Screening in localdate datatypes
     * @return An Screening event wrapped in optional to match the below method.
     */
    //Coming soon
    public static Optional<Event> getScreening(String pname, LocalDate alocaldate){
        assert pname != null;
        assert alocaldate != null;
        Event c1 = new Screening(pname,alocaldate);
        abstractevent.addtoAvents(c1);
        return Optional.of(c1);
    }

    /**
     * Static factory method for normal Screening event. It requries flyweight check
     * @param pname name of the Screening in natural language
     * @param plocation Location of the Screening in Location enum
     * @param plocaldate date of the Screening in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this Screening.
     * @param ptotalticketnum int that shows the number of tickets for this Screening.
     * @return Rating of the Screening in Rating enum
     */
    //need to deal with location optional
    public static Optional<Event> getScreening(String pname, Location plocation, LocalDate plocaldate,
                                            double ppriceperperson, int ptotalticketnum, Rating prating){
        assert pname != null;
        assert plocation != null;
        assert plocaldate != null;
        assert prating != null;
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
        Event c1 = new Screening(pname,plocation,plocaldate,ppriceperperson,ptotalticketnum,prating);
        abstractevent.addtoAvents(c1);
        return Optional.of(c1);
    }

    /**
     * Override the Abstractevent method to explore polymorphism since for concert we do have an artist so we do not
     * want to throw an exception instead we should set pArtist to the pArtist passed in
     * @param arating the rating that we want to set this's ratings to.
     */
    public void setArating(Rating arating) {
        this.arating = Optional.of(arating);
    }

    /**
     * The accept Visitor method for visitor design pattern used for visitor
     * @param pVisitor a Vistor type that want to visit this Screening.
     */

    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitScreening(this);
    }
}
