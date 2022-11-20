package src;

import java.util.Iterator;

public class Vipvisitor implements Visitor{
    private int numofvips;

    /**
     * Constructor for vip visitor
     */
    public Vipvisitor() {
        this.numofvips = 0;
    }

    /**
     * Getter to get the numofvips field
     * @return int representing the number of vips that we counted during traversing
     */
    public int getNumofvips() {
        return numofvips;
    }

    /**
     * Visit a Filter Result by iterating through all it's event stored
     * @param pfilter The Filter Result to be visited
     */
    @Override
    public void visitFilterResult(FilterResult pfilter) {
        for(Iterator<Event> i = pfilter.getaFilteredEvents(); i.hasNext(); )
        {
            i.next().accept(this);
        }
    }

    /**
     * Count the vip field's size and add them to the fields storing the number of vips
     * @param pconcert concert that we want to count how many vips are there
     */
    @Override
    public void visitConcert(Concert pconcert) {
        numofvips += pconcert.getVips().size();
    }

    /**
     * Count the vip field's size and add them to the fields storing the number of vips
     * @param pgala gala that we want to count how many vips are there
     */
    @Override
    public void visitGala(Gala pgala) {
        numofvips += pgala.getVips().size();
    }

    /**
     * Add 0 since Workshop have no vips
     * @param pwork workshop that we want to count how many vips are there
     */
    @Override
    public void visitWorkshop(Workshops pwork) {
        this.numofvips += 0;
        //do nothing
    }

    /**
     * add 0 since Screening have no vips
     * @param pscreen screening that we want to count how many vips are there
     */
    @Override
    public void visitScreening(Screening pscreen) {
        this.numofvips += 0;
        //do nothing
    }

    /**
     * Traverse all the events within the Festival and get them to accept this visitor
     * @param pfest festival that we want to count how many vips are there
     */
    @Override
    public void visitFestival(Festival pfest) {
        for(Event p:pfest.getEvents()){
            p.accept(this);
        }
        //do nothing
    }


}
