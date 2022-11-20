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

public class FilterResultnVistorTest {
    //This test is dedicated to filter result and vistor since they will be used together
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
    static ArrayList<Event> temp2;
    static FilterResult fr;
    static Visitor v1;
    static Visitor v2;

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
        temp2 = new ArrayList<>();
        temp2.add(e1.get());
        temp2.add(e2.get());
        temp2.add(e3.get());
        temp2.add(w3.get());
        temp2.add(fest1.get());
        fr = new FilterResult(temp2);
        v1 = new ProfitVisitor(0.5,0.4,0.3,0.2);
        v2 = new Vipvisitor();
        VIPS vip1 = new VIPS();
        VIPS vip2 = new VIPS();
        e1.get().addvips(vip1);
        e1.get().addvips(vip2);
        e2.get().addvips(vip1);
    }
    static class DateFilter implements FilterStrategy{
        LocalDate check;
        DateFilter(LocalDate tocheck){
            this.check = tocheck;
        }
        @Override
        public boolean filter(Event pevent) {
            return pevent.getDate().equals(this.check);
        }
    }
    @Test
    void TestwithStubs1(){
        FilterStrategy stub1 = new DateFilter(date);
        FilterResult check = fr.Filter(stub1);
        try {
            Field f1 = FilterResult.class.getDeclaredField("aFilteredEvents");
            f1.setAccessible(true);
            Object o1 = f1.get(check);
            ArrayList<Event> temp2 = (ArrayList<Event>) o1;
            ArrayList<Event> real = new ArrayList<>();
            real.add(e1.get());
            real.add(e2.get());
            real.add(e3.get());
            real.add(fest1.get());
            assertEquals(temp2,real);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    void TestwithStubs2(){
        FilterStrategy stub1 = new DateFilter(date);
        FilterResult check = fr.Filter(stub1);
        ProfitVisitor v3 = new ProfitVisitor(0.1,0.2,0.3,0.4);
        check.accept(v3);
        assertEquals(v3.getProfit(),10.0*50*0.2*2 + 9.0*40*0.1*2 + 4.0*60*0.3);
    }
    @Test
    void Test1(){
        FilterResult check = fr.Filter(f1);
        try {
            Field f1 = FilterResult.class.getDeclaredField("aFilteredEvents");
            f1.setAccessible(true);
            Object o1 = f1.get(check);
            ArrayList<Event> temp2 = (ArrayList<Event>) o1;
            ArrayList<Event> real = new ArrayList<>();
            real.add(e1.get());
            real.add(w3.get());
            assertEquals(temp2,real);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    void Test2(){
        FilterResult check = fr.Filter(f2);
        try {
            Field f1 = FilterResult.class.getDeclaredField("aFilteredEvents");
            f1.setAccessible(true);
            Object o1 = f1.get(check);
            ArrayList<Event> temp2 = (ArrayList<Event>) o1;
            ArrayList<Event> real = new ArrayList<>();
            real.add(e1.get());
            real.add(e2.get());
            assertEquals(temp2,real);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    void Test3(){
        FilterResult check = fr.Filter(f3);
        try {
            Field f1 = FilterResult.class.getDeclaredField("aFilteredEvents");
            f1.setAccessible(true);
            Object o1 = f1.get(check);
            ArrayList<Event> temp2 = (ArrayList<Event>) o1;
            ArrayList<Event> real = new ArrayList<>();
            real.add(e1.get());
            real.add(e2.get());
            real.add(w3.get());
            assertEquals(temp2,real);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    void Test4(){
        FilterResult check = fr.Filter(f4);
        try {
            Field f1 = FilterResult.class.getDeclaredField("aFilteredEvents");
            f1.setAccessible(true);
            Object o1 = f1.get(check);
            ArrayList<Event> temp2 = (ArrayList<Event>) o1;
            ArrayList<Event> real = new ArrayList<>();
            real.add(e1.get());
            assertEquals(temp2,real);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    void Test5(){
        fr.accept(v1);
        ProfitVisitor v3 = (ProfitVisitor)v1;
        assertEquals(v3.getProfit(),2*0.4*10.0*50 + 2*0.5*9.0*40 + 0.3*2*4.0*60);
    }
    @Test
    void Test6(){
        fr.accept(v2);
        Vipvisitor v4 = (Vipvisitor) v2;
        assertEquals(v4.getNumofvips(),6);
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
