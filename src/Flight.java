import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Flight extends RandomGenerator {
    private static final int numOfFlights = 15;
    private static final String[][] flightSchedule = new String[numOfFlights][3];
    private static int nextDays = 0;

    public static void main(String[] args) {
        RandomGenerator r1 = new RandomGenerator();
        for (int i = 0; i < numOfFlights; i++) {
            flightSchedule[i][0] = createNewFlightsAndTime();
            flightSchedule[i][1] = "\tFlight Number : ";
            flightSchedule[i][2] = r1.randomFlightNumbGen().toUpperCase();
        }
        for (String[] strings : flightSchedule) {
            for (String string : strings) {
                System.out.printf("%s\t", string);
            }
            System.out.println();
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
        return date.format(DateTimeFormatter.ofPattern("EEEE, \tMMMM dd yyyy, HH:mm a "));
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