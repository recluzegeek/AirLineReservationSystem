public class FlightBookingAndReserving implements DisplayFlight {
    Customer c1 = new Customer();
    Flight flight = new Flight();

    void bookFlight(String flightNo, int numOfTickets, String userID) {
        System.out.println("User ID is : " + userID);
        boolean isFound = false;
        for (Flight f1 : flight.getFlightList()) {
            if (flightNo.equalsIgnoreCase(f1.getFlightNumber())) {
                for (Customer customer : c1.customerCollection) {
                    if (userID.equals(customer.getUserID())) {
                        isFound = true;
                        f1.setNoOfSeatsInTheFlight(f1.getNoOfSeats() - numOfTickets);
                        customer.addFlightToUserArray(f1);
                        System.out.println("Flight size of the customer " + customer.getName() + " is : " + customer.getFlightsRegisteredByUser().length);
                        f1.displayFlightSchedule();
                        break;
                    }
                }
            }
        }
        if (!isFound) {
            System.out.println("Invalid Flight Number...! No flight with the  ID \"" + flightNo + "\" was found...");
        }
    }

    public String toString(int serialNum, Flight flights) {
        return String.format("| %-5d| %-41s | %-9s | \t%-9s | %-21s | %-22s | %-10s  |   %-6sHrs |  %-4s  |", serialNum, flights.getFlightSchedule(), flights.getFlightNumber(),
                flights.getNoOfSeatsInTheFlight(), flights.getFromWhichCity(), flights.getToWhichCity(), flights.getArrivalTime(), flights.getFlightTime(), flights.getGate());
    }

    @Override
    public void displayFlightsRegisteredByUser(String userID) {
        System.out.println();
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+\n");
        System.out.printf("| Num  | FLIGHT SCHEDULE\t\t\t   | FLIGHT NO | Available Seats  | \tFROM ====>>       | \t====>> TO\t   | \t    ARRIVAL TIME       | FLIGHT TIME |  GATE  |%n");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+\n");

        for (Customer customer : c1.customerCollection) {
            Flight[] f = customer.getFlightsRegisteredByUser();
            if (userID.equals(customer.getUserID())) {
                for (int i = 0; i < customer.getNumOfFlights(); i++) {
                    System.out.println(toString((i + 1), f[i]));
                    System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+\n");
                }
            }
        }
    }
}
