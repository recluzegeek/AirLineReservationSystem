import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Flight {
    private final String flightSchedule;
    private final String flightNumber;
    private final String fromWhichCity;
    private final String toWhichCity;
    private final String gate;
    private final int noOfSeatsInTheFlight;

    private static final int numOfFlights = 20;
    private static int nextDays = 0;
    private final List<Flight> flightList = new ArrayList<>();


    Flight() {
        this.flightSchedule = null;
        this.flightNumber = null;
        this.noOfSeatsInTheFlight = 0;
        toWhichCity = null;
        fromWhichCity = null;
        this.gate = null;
    }

    Flight(String flightSchedule, String flightNumber, int noOfSeatsInTheFlight, String[] chosenDestinations, String gate) {
        this.flightSchedule = flightSchedule;
        this.flightNumber = flightNumber;
        this.noOfSeatsInTheFlight = noOfSeatsInTheFlight;
        this.fromWhichCity = chosenDestinations[0];
        this.toWhichCity = chosenDestinations[1];
        this.gate = gate;
    }

    public void displayFlightSchedule() {
        Iterator<Flight> flightIterator = flightList.iterator();
        System.out.println();
        System.out.print("+------------+-----------------------------------------------+--------------+------------------+--------------------------+------------------------+---------------+----------+\n");
        System.out.printf("| SerialNum  | FLIGHT SCHEDULE\t\t\t\t     | FLIGHT NO    | NO OF PASSENGERS | \tFROM ====>>       | \t====>> TO\t   | ARRIVAL TIME  |   GATE   |%n");
        System.out.print("+------------+-----------------------------------------------+--------------+------------------+--------------------------+------------------------+---------------+----------+\n");
        int i = 0;
        while (flightIterator.hasNext()) {
            i++;
            Flight f1 = flightIterator.next();
            System.out.println(f1.toString(i));
            System.out.print("+------------+-----------------------------------------------+--------------+------------------+--------------------------+------------------------+---------------+----------+\n");
        }
    }

    public String toString(int i) {
        return String.format("|   %-8d | %-45s |  %-10s  | \t\t %-5s | %-24s | %-22s |    \t   | %-5s    |", i, flightSchedule, flightNumber, noOfSeatsInTheFlight, fromWhichCity, toWhichCity, gate);
    }

    public void flightScheduler() {
        RandomGenerator r1 = new RandomGenerator();
        for (int i = 0; i < numOfFlights; i++) {
            String flightSchedule = createNewFlightsAndTime();
            String flightNumber = r1.randomFlightNumbGen(2, 1).toUpperCase();
            int noOfSeatsInTheFlight = r1.randomNumOfSeats();
            String[] chosenDestinations = r1.randomDestinations();
            String gate = r1.randomFlightNumbGen(1, 50);
            flightList.add(new Flight(flightSchedule, flightNumber, noOfSeatsInTheFlight, chosenDestinations, gate.toUpperCase()));
        }
    }

    public static String createNewFlightsAndTime() {
        Calendar c = Calendar.getInstance();
        nextDays += Math.random() * 10;
        c.add(Calendar.DATE, nextDays);
        c.add(Calendar.HOUR, nextDays);
        c.set(Calendar.MINUTE, ((c.get(Calendar.MINUTE) * 3) - (int) (Math.random() * 45)));
        Date myDateObj = c.getTime();
        LocalDateTime date = Instant.ofEpochMilli(myDateObj.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        date = getNearestHourQuarter(date);
        return date.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm a "));
    }

    public static LocalDateTime getNearestHourQuarter(LocalDateTime datetime) {
        int minutes = datetime.getMinute();
        int mod = minutes % 15;
        LocalDateTime newDatetime;
        if (mod < 8) {
            newDatetime = datetime.minusMinutes(mod);
        } else {
            newDatetime = datetime.plusMinutes(15 - mod);
        }
        newDatetime = newDatetime.truncatedTo(ChronoUnit.MINUTES);
        return newDatetime;
    }
}