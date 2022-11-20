package src;

import javax.xml.stream.util.EventReaderDelegate;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Driver {
    public static void main(String args[]){
        EventManagement EventBrite = EventManagement.getEventManagement();
        LocalDate date = LocalDate.of(2020, 1, 8);
        LocalDate date2 = LocalDate.of(2030, 1, 8);
        EventBrite.addConcertEvent("Barry", Location.BellCentre,date,10.0,50,"Justin Bieber");
        try{
            EventBrite.addGalaEvent("Barry", Location.BellCentre,date,10.0,50);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        EventBrite.addGalaEvent("Barry", Location.OlympicStadium,date,10.0,50);
        EventBrite.addWorkshopEvent("Barry", Location.BellCentre,date2,4.0,60);
        EventBrite.addScreeningEvent("PPP",Location.PlaceDesArts,date2,4.5,20,Rating.PG13);
        EventBrite.addConcertEvent("X",date);
        EventBrite.addWorkshopEvent("X2",date);
        EventBrite.addGalaEvent("X3",date);
        EventBrite.addScreeningEvent("X4",date);
        ArrayList<Event> star = EventBrite.getHostedEvents();
        EventBrite.addFestival(star,"Best Festival");
        FilterStrategy f1 = new LocationFilter(Location.BellCentre);
        FilterStrategy f2 = new PriceFilter(4.2,11.0);
        System.out.println("Below is the profit of the events within the price range 4.2-11.0 with below multiplyer ");
        System.out.println("Concert:0.5, Gala:0.4, Workshop:0.3, Screening:0.1");
        System.out.println(EventBrite.profitcalculator(f2,0.5,0.4,0.3,0.1));
        System.out.println("Below is the profit of the events hold in bell center with below multiplyer ");
        System.out.println("Concert:0.5, Gala:0.4, Workshop:0.3, Screening:0.1");
        System.out.println(EventBrite.profitcalculator(f1,0.5,0.4,0.3,0.1));
        try{
            EventBrite.getprice(Location.PlaceDesArts,date);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("The price for the event hold on Jan 18 2020 in Olympic Satdium have price");
        System.out.println(EventBrite.getprice(Location.OlympicStadium,date));
        System.out.println("The name for the event hold on Jan 18 2020 in Multiple have price"); //should be fest1
        System.out.println(EventBrite.getEventName(Location.Multiple,date));
        System.out.println("The number of tickets for the event hold on Jan 18 2030 in BellCenter have price");
        System.out.println(EventBrite.getnumtickets(Location.BellCentre,date2));
        System.out.println("Date at index 2: ");
        System.out.println(EventBrite.getDate(2));
        System.out.println("Change location at index 1 to ParcJeanDrapeau");
        EventBrite.setLocation(1,Location.ParcJeanDrapeau);
        System.out.println("New location: ");
        System.out.println(EventBrite.getLocation(1));
        System.out.println("Change the price of the concert at index 0 to 15.5");
        EventBrite.setApriceperperson(Location.BellCentre,date,15.5);
        System.out.println("New price: ");
        System.out.println(EventBrite.getprice(Location.BellCentre,date));
        System.out.println("Change the number of tickets for the concert at index 0 to 200");
        EventBrite.setAtotalticketnum(Location.BellCentre,date,200);
        System.out.println("New number of tickets: ");
        System.out.println(EventBrite.getnumtickets(Location.BellCentre,date));
        EventBrite.addpreq(Location.BellCentre,date2,5);
        Workshops w1 = (Workshops) EventBrite.getHostedEvents().get(5);
        //Java reflection to do functional test since we do not want the user to get those hidden information
        //if failed an error will be thrown. The below is to check if no error was thrown did the program behave correctly
        try {
            Field Fi1 = Workshops.class.getDeclaredField("prereq");
            Fi1.setAccessible(true);
            ArrayList<Workshops> pre = (ArrayList<Workshops>) Fi1.get(w1);
            if(EventBrite.getHostedEvents().get(2).equals(pre.get(0))){
                System.out.println("Success");
            }
            else{
                System.out.println("Fail");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        VIPS v1 = new VIPS();
        EventBrite.addvips(Location.ParcJeanDrapeau,date,v1);
        Gala g1 = (Gala) EventBrite.getHostedEvents().get(1);
        try {
            Field Fi1 = Gala.class.getDeclaredField("vips");
            Fi1.setAccessible(true);
            ArrayList<VIPS> vippp = (ArrayList<VIPS>) Fi1.get(g1);
            if(v1.equals(vippp.get(0))){
                System.out.println("Success");
            }
            else{
                System.out.println("Fail");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String newArtist = "XXX";
        EventBrite.setpArtist(Location.BellCentre,date,newArtist);
        Concert c1 = (Concert) EventBrite.getHostedEvents().get(0);
        try {
            Field Fi1 = Concert.class.getDeclaredField("pArtist");
            Fi1.setAccessible(true);
            Optional<String> art = (Optional<String>) Fi1.get(c1);
            if(Optional.of(newArtist).equals(art)){
                System.out.println("Success");
            }
            else{
                System.out.println("Fail");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        EventBrite.setArating(Location.PlaceDesArts,date2,Rating.PG);
        Screening s1 = (Screening) EventBrite.getHostedEvents().get(3);
        try {
            Field Fi1 = Screening.class.getDeclaredField("arating");
            Fi1.setAccessible(true);
            Optional<Rating> rat = (Optional<Rating>) Fi1.get(s1);
            if(Optional.of(Rating.PG).equals(rat)){
                System.out.println("Success");
            }
            else{
                System.out.println("Fail");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Below is the number of vips for the events within the price range 4.2-11.0 ");
        System.out.println(EventBrite.vipcalculator(f2));
        System.out.println("Below is the number of vips for the events held in bell center ");
        System.out.println(EventBrite.vipcalculator(f1));
    }
}
