public class FlightBookingAndReserving extends Flight {

    void bookFlight(String flightNo, int numOfTickets) {
        boolean isFound = false;
        for (Flight f1 : getFlightList()) {
            if (flightNo.equalsIgnoreCase(f1.getFlightNumber())) {
                isFound = true;
                f1.setNoOfSeatsInTheFlight(f1.getNoOfSeats() - numOfTickets);
                displayFlightSchedule();
                break;
            }
        }
        if (!isFound) {
            System.out.println("Invalid Flight Number...! No flight with the  ID \"" + flightNo + "\" was found...");
        }


    }

    void displayRegisteredFlightsByUser(String userID) {

    }
}
