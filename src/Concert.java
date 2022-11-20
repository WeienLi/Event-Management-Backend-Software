package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Concert extends abstractevent{
    private Optional<String> pArtist;
    private final ArrayList<VIPS> vips = new ArrayList<>();

    /**
     * private flyweight constructor that construct a normal concert event with all information known
     * @param pname name of the concert in natural language
     * @param plocation Location of the concert in Location enum
     * @param plocaldate date of the concert in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this concert.
     * @param ptotalticketnum int that shows the number of tickets for this concert.
     * @param pArtist name of the artist in natural language.
     */
    private Concert(String pname, Location plocation, LocalDate plocaldate,
                   double ppriceperperson, int ptotalticketnum, String pArtist) {
        super(pname, plocation, plocaldate, ppriceperperson, ptotalticketnum);
        this.pArtist = Optional.of(pArtist);
    }

    /**
     * private flyweight constructor that construct a coming soon concert event
     * @param pname name of the concert in natural language
     * @param alocaldate date of the concert in localdate datatypes
     */
    private Concert(String pname, LocalDate alocaldate) {
        super(pname, alocaldate);
        this.pArtist = Optional.empty();
    }
    //Coming soon

    /**
     * Static factory method for coming soon event. It does not requires the flyweight check since location has yet to be
     * set.
     * @param pname pname name of the concert in natural language
     * @param alocaldate date of the concert in localdate datatypes
     * @return An Concert event wrapped in optional to match the below method.
     */
    public static Optional<Event> getConert(String pname, LocalDate alocaldate){
        assert pname != null;
        assert alocaldate != null;
        Event c1 = new Concert(pname,alocaldate);
        abstractevent.addtoAvents(c1);
        return Optional.of(c1);
    }

    /**
     * Static factory method for normal concert event. It requries flyweight check
     * @param pname name of the concert in natural language
     * @param plocation Location of the concert in Location enum
     * @param plocaldate date of the concert in localdate datatypes
     * @param ppriceperperson double that shows the price per ticket for this concert.
     * @param ptotalticketnum int that shows the number of tickets for this concert.
     * @param pArtist name of the artist in natural language.
     * @return An optional event that is present if it passes the flyweight check and the event is created and returned.
     * And is not present otherwise.
     */
    //need to deal with location optional
    public static Optional<Event> getConert(String pname, Location plocation, LocalDate plocaldate,
                                  double ppriceperperson, int ptotalticketnum, String pArtist){
        assert pname != null;
        assert plocation != null;
        assert plocaldate != null;
        assert pArtist != null;
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
        Event c1 = new Concert(pname,plocation,plocaldate,ppriceperperson,ptotalticketnum,pArtist);
        abstractevent.addtoAvents(c1);
        return Optional.of(c1);
    }

    /**
     * Override the Abstractevent method to explore polymorphism since for concert we do have vips so we do not want to
     * throw an exception instead we should add it to the list
     * @param v1 The vip that we want to add to this's vip list
     */
    @Override
    public void addvips(VIPS v1){
        // no need of deepcopy vips having nothing
        vips.add(v1);
    }

    /**
     * Override the Abstractevent method to explore polymorphism since for concert we do have an artist so we do not
     * want to throw an exception instead we should set pArtist to the pArtist passed in
     * @param pArtist the Artist that we want to set this's artist to.
     */
    @Override
    public void setpArtist(String pArtist) {
        this.pArtist = Optional.of(pArtist);
    }

    /**
     * Get the vip Arraylist no need to deepcopy since vip is an empty class and test
     * @return a copy of the vips arraylist
     */
    public ArrayList<VIPS> getVips() {
        return new ArrayList<VIPS>(vips);
    }

    /**
     * The accept Visitor method for visitor design pattern used for visitor
     * @param pVisitor a Vistor type that want to visit this concert.
     */
    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitConcert(this);
    }
}
