package src;

import java.time.LocalDate;
import java.util.Optional;

/*
Representation of a type of Event that can exist
 */
public interface Event extends Visitable{
    String getName();
    Optional<Location> getLocation();
    LocalDate getDate();
    Optional<Double> getPrice();
    Optional<Integer> getNumTickets();
    void setAlocation(Location alocation) throws IllegalArgumentException;
    void setApriceperperson(double apriceperperson);
    void setAtotalticketnum(int atotalticketnum);
    void addpreq(Workshops w1) throws UnsupportedOperationException;
    void addvips(VIPS v1) throws UnsupportedOperationException;
    void setpArtist(String pArtist) throws UnsupportedOperationException;
    void setArating(Rating arating) throws UnsupportedOperationException;
}
