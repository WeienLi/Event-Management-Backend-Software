package Tests;

import com.sun.source.tree.UsesTree;
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
public class FilterTest {
    static FilterStrategy f1;
    static FilterStrategy f2;
    static FilterStrategy f3;
    static FilterStrategy f4;
    static Optional<Event> e1;
    static LocalDate date2;
    static LocalDate date;
    static Optional<Event> e2;
    static Optional<Event> e3;
    static Optional<Event> w3;
    static Optional<Event> fest1;
    static ArrayList<Event> temp;
    @BeforeAll
    static void setup(){
        f1 = new LocationFilter(Location.BellCentre);
        f2 = new PriceFilter(5.0,10.0);
        f3 = new Orfilter(f1,f2);
        f4 = new AndFilter(f1,f2);
        date = LocalDate.of(2020, 1, 8);
        date2 = LocalDate.of(2030, 1, 8);
        e1 = Gala.getGala("Barry", Location.BellCentre,date,10.0,50);
        e2 = Concert.getConert("Tour", Location.OlympicStadium,date,9.0,40,"X");
        e3 = Screening.getScreening("Tour",date);
        w3 = Workshops.getWorkshop("Barry", Location.BellCentre,date2,4.0,60);
        temp = new ArrayList<>();
        temp.add(e1.get());
        temp.add(e2.get());
        temp.add(e3.get());
        temp.add(w3.get());
        fest1 = Festival.getFestival(temp,"EHH");
    }
    @Test
    //for filter1
    void Test1(){
        assertEquals(f1.filter(e1.get()),true);
        assertEquals(f1.filter(e2.get()),false);
        assertEquals(f1.filter(e3.get()),false);
        assertEquals(f1.filter(w3.get()),true);
        assertEquals(f1.filter(fest1.get()),false);
    }
    @Test
    void Test2(){
        assertEquals(f2.filter(e1.get()),true);
        assertEquals(f2.filter(e2.get()),true);
        assertEquals(f2.filter(e3.get()),false);
        assertEquals(f2.filter(w3.get()),false);
        assertEquals(f2.filter(fest1.get()),false);
    }
    @Test
    void Test3(){
        assertEquals(f3.filter(e1.get()),true);
        assertEquals(f3.filter(e2.get()),true);
        assertEquals(f3.filter(e3.get()),false);
        assertEquals(f3.filter(w3.get()),true);
        assertEquals(f3.filter(fest1.get()),false);
    }
    @Test
    void Test4(){
        assertEquals(f4.filter(e1.get()),true);
        assertEquals(f4.filter(e2.get()),false);
        assertEquals(f4.filter(e3.get()),false);
        assertEquals(f4.filter(w3.get()),false);
        assertEquals(f4.filter(fest1.get()),false);
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
