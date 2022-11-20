package Tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import src.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GalaTest {
    static Optional<Event> e1;
    static LocalDate date2;
    static LocalDate date;
    static Optional<Event> e2;
    static Optional<Event> e3;
    static Optional<Event> w3;
    @BeforeAll
    static void setup(){
        date = LocalDate.of(2020, 1, 8);
        date2 = LocalDate.of(2030, 1, 8);
        e1 = Gala.getGala("Barry", Location.BellCentre,date,10.0,50);
        e2 = Gala.getGala("Tour", Location.OlympicStadium,date,9.0,40);
        e3 = Gala.getGala("Tour",date);
        w3 = Workshops.getWorkshop("Barry", Location.PlaceDesArts,date2,100.0,60);
    }
    @Test
    void test1(){
        //e1 = Concert.getConert("Barry", Location.BellCentre,date,10.0,50,"Justin Bieber");
        Optional<Event> e2 = Concert.getConert("Tour", Location.BellCentre,date,9.0,40,"The kid Laori");
        Optional<Event> e3 = Gala.getGala("Barry", Location.BellCentre,date,3.0,90);
        Optional<Event> e4 = Screening.getScreening("Barry", Location.BellCentre,date,8.0,20,Rating.G);
        Optional<Event> e5 = Workshops.getWorkshop("Barry", Location.BellCentre,date,100.0,60);
        assertEquals(e2,Optional.empty());
        assertEquals(e3,Optional.empty());
        assertEquals(e4,Optional.empty());
        assertEquals(e5,Optional.empty());
    }
    @Test
    void test2(){
        Gala e4 = (Gala) e3.get();
        assertEquals(e4.getName(),"Tour");
        assertEquals(e4.getDate(),date);
        assertEquals(e4.getLocation(),Optional.empty());
        assertEquals(e4.getNumTickets(),Optional.empty());
        assertEquals(e4.getPrice(),Optional.empty());
        assertEquals(Gala.getAevents().size(),4);
    }

    @Test
    void test4(){

        Workshops w1 = (Workshops) w3.get();
        //Optional<Event> e1 = Concert.getConert("Barry",Location.BellCentre,date2,10.0,3,"Juice");
        Event e10 = e1.get();
        try {
            e10.addpreq(w1);
            fail();
        }
        catch (UnsupportedOperationException e){
            //pass
        }
    }
    @Test
    void test5(){
        VIPS v1 = new VIPS();
        Event e10 = e1.get();
        try{
            e10.addvips(v1);
        }
        catch (UnsupportedOperationException e){
            fail();
        }
        try{
            Field f1 = Gala.class.getDeclaredField("vips");
            f1.setAccessible(true);
            Object o1 = f1.get(e10);
            ArrayList<VIPS> test = new ArrayList<>();
            test.add(v1);
            assertEquals(o1,test);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    void test6(){
        String newArtist = "Juice Wrld";
        Event e10 = e1.get();
        try {
            e10.setpArtist(newArtist);
            fail();
        }
        catch (UnsupportedOperationException e){

        }
    }
    @Test
    void test7(){
        Event e10 = e1.get();
        try {
            e10.setArating(Rating.R);
            fail();
        }
        catch (UnsupportedOperationException e){
            //pass
        }
    }

    //clear flyweight
    @AfterAll
    static void cleanup() throws NoSuchFieldException, IllegalAccessException {
        Field f1 = abstractevent.class.getDeclaredField("aevents");
        f1.setAccessible(true);
        Object o = f1.get(null);
        ArrayList<Event> pp = (ArrayList<Event>) o;
        pp.clear();
    }
}
