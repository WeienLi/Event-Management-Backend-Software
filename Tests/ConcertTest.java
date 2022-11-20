package Tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ConcertTest {
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
        e1 = Concert.getConert("Barry", Location.BellCentre,date,10.0,50,"Justin Bieber");
        e2 = Concert.getConert("Tour", Location.OlympicStadium,date,9.0,40,"The kid Laori");
        e3 = Concert.getConert("Tour",date);
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
    //reflection to test getter is correct or not
    //test whether field == getters == value we passed in
    //below test was an event test in general since thos method are implemented within Abtract class
    @Test
    void test2(){
        Concert e3 = (Concert) e2.get();
        try {
            Field f1 = abstractevent.class.getDeclaredField("aname");
            Field f2 = abstractevent.class.getDeclaredField("alocation");
            Field f3 = abstractevent.class.getDeclaredField("alocaldate");
            Field f4 = abstractevent.class.getDeclaredField("apriceperperson");
            Field f5 = abstractevent.class.getDeclaredField("atotalticketnum");
            f1.setAccessible(true);
            f2.setAccessible(true);
            f3.setAccessible(true);
            f4.setAccessible(true);
            f5.setAccessible(true);
            Object o1 = f1.get(e3);
            Object o2 = f2.get(e3);
            Object o3 = f3.get(e3);
            Object o4 = f4.get(e3);
            Object o5 = f5.get(e3);
            assertEquals(o1,e3.getName());
            assertEquals(o2,e3.getLocation());
            assertEquals(o3,e3.getDate());
            assertEquals(o4,e3.getPrice());
            assertEquals(o5,e3.getNumTickets());
            assertEquals(o1,"Tour");
            assertEquals(o2,Optional.of(Location.OlympicStadium));
            assertEquals(o3,date);
            assertEquals(o4,Optional.of(9.0));
            assertEquals(o5,Optional.of(40));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test3(){
        Concert e4 = (Concert) e3.get();
        assertEquals(e4.getName(),"Tour");
        assertEquals(e4.getDate(),date);
        assertEquals(e4.getLocation(),Optional.empty());
        assertEquals(e4.getNumTickets(),Optional.empty());
        assertEquals(e4.getPrice(),Optional.empty());
        assertEquals(Concert.getAevents().size(),4);
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
            Field f1 = Concert.class.getDeclaredField("vips");
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
        }
        catch (UnsupportedOperationException e){
            fail();
        }
        try {
            Field f1 = Concert.class.getDeclaredField("pArtist");
            f1.setAccessible(true);
            Object o1 = f1.get(e10);
            assertEquals(o1,Optional.of(newArtist));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
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

    //below test if for all event since it is implemented in abstract event and were never override
    @Test
    void test8(){
        Event e10 = e1.get();
        try{
            e10.setAlocation(Location.OlympicStadium); //will not change since not allowed
            fail();
        }
        catch (IllegalArgumentException e){

        }
    }
    @Test
    void test9(){
        Event e10 = e1.get();
        try{
            e10.setAlocation(Location.PlaceDesArts); //will not change since not allowed
            assertEquals(e10.getLocation(),Optional.of(Location.PlaceDesArts));
        }
        catch (IllegalArgumentException e){
            fail();
        }
    }
    @Test
    void test10(){
        Event e10 = e1.get();
        e10.setApriceperperson(50.0);
        assertEquals(e10.getPrice(),Optional.of(50.0));
    }
    @Test
    void test11(){
        Event e10 = e1.get();
        e10.setAtotalticketnum(20);
        assertEquals(e10.getNumTickets(),Optional.of(20));
    }
    //clean up flyweight stored
    @AfterAll
    static void cleanup() throws NoSuchFieldException, IllegalAccessException {
        Field f1 = abstractevent.class.getDeclaredField("aevents");
        f1.setAccessible(true);
        Object o = f1.get(null);
        ArrayList<Event> pp = (ArrayList<Event>) o;
        pp.clear();
    }
}
