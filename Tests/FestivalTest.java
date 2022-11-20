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

public class FestivalTest {
    static Optional<Event> e1;
    static LocalDate date2;
    static LocalDate date;
    static Optional<Event> e2;
    static Optional<Event> e3;
    static Optional<Event> w3;
    static Optional<Event> f1;
    static ArrayList<Event> temp;
    @BeforeAll
    static void setup(){
        date = LocalDate.of(2020, 1, 8);
        date2 = LocalDate.of(2030, 1, 8);
        e1 = Gala.getGala("Barry", Location.BellCentre,date,10.0,50);
        e2 = Concert.getConert("Tour", Location.OlympicStadium,date,9.0,40,"X");
        e3 = Screening.getScreening("Tour",date);
        w3 = Workshops.getWorkshop("Barry", Location.PlaceDesArts,date2,100.0,60);
        temp = new ArrayList<>();
        temp.add(e1.get());
        temp.add(e2.get());
        temp.add(e3.get());
        temp.add(w3.get());
        f1 = Festival.getFestival(temp,"EHH");
    }

    @Test
     void Test1(){
        Festival p = (Festival) f1.get();
        assertEquals(p.getEvents(),temp);
        assertEquals(p.getName(),"EHH");
        assertEquals(p.getDate(),date);
        assertEquals(p.getLocation(),Optional.of(Location.Multiple));
        assertEquals(p.getPrice(),Optional.of(119.0 * 0.2));
        assertEquals(p.getNumTickets(),Optional.of(40));
    }
    @Test
    //fail to create
    void Test2(){
        Optional<Event> f2 = Festival.getFestival(temp,"LOL");
        assertEquals(f2,Optional.empty());
    }
    @AfterAll
    static void cleanup() throws NoSuchFieldException, IllegalAccessException {
        Field f1 = abstractevent.class.getDeclaredField("aevents");
        f1.setAccessible(true);
        Object o = f1.get(null);
        ArrayList<Event> pp = (ArrayList<Event>) o;
        pp.clear();
    }
}
