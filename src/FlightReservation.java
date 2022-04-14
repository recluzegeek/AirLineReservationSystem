import java.util.Iterator;
import java.util.List;

public class FlightReservation {
    Customer c1 = new Customer();
    Flight flight = new Flight();

    void bookFlight(String flightNo, int numOfTickets, String userID) {
        boolean isFound = false;
        for (Flight f1 : flight.getFlightList()) {
            if (flightNo.equalsIgnoreCase(f1.getFlightNumber())) {
                for (Customer customer : c1.customerCollection) {
                    if (userID.equals(customer.getUserID())) {
                        isFound = true;
                        f1.setNoOfSeatsInTheFlight(f1.getNoOfSeats() - numOfTickets);

                        f1.addCustomerToFlight(customer);
                        if (isAlreadyAdded(customer.flightsRegisteredByUser, f1)) {
//                            customer.addFlightToUserArray(f1,numOfTickets);
                            addNumberOfTicketsToUser(customer.flightsRegisteredByUser, f1, customer, numOfTickets);
                        } else {
                            customer.addFlightToUserArray(f1);
                            addNumberOfTicketsToUser(customer, numOfTickets);
                            break;
                        }
                    }
                }
            }
        }
        if (!isFound) {
            System.out.println("Invalid Flight Number...! No flight with the  ID \"" + flightNo + "\" was found...");
        } else {
            System.out.printf("\n %50s You've booked %d tickets for Flight \"%5s\"...", "", numOfTickets, flightNo.toUpperCase());
        }
    }

    void addNumberOfTicketsToUser(List<Flight> flightList, Flight flight, Customer customer, int numOfTickets) {
        int index = indexOfAlreadyAdded(flightList, flight);
        int newNumOfTickets = customer.numOfTicketsBookedByUser.get(index) + numOfTickets;
        customer.numOfTicketsBookedByUser.set(index,newNumOfTickets);
    }

    /*toString() Method for displaying number of flights registered by single user...*/
    public String toString(int serialNum, Flight flights, Customer customer) {
        return String.format("| %-5d| %-41s | %-9s | \t%-9d | %-21s | %-22s | %-10s  |   %-6sHrs |  %-4s  |", serialNum, flights.getFlightSchedule(), flights.getFlightNumber(),
                customer.numOfTicketsBookedByUser.get(serialNum - 1), flights.getFromWhichCity(), flights.getToWhichCity(), flights.getArrivalTime(), flights.getFlightTime(), flights.getGate());
    }

    boolean isAlreadyAdded(List<Flight> flightList, Flight flight) {
        boolean addedOrNot = false;
        for (Flight flight1 : flightList) {
            if (flight1.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
                addedOrNot = true;
                break;
            }
        }
        return addedOrNot;
    }

    int indexOfAlreadyAdded(List<Flight> flightList, Flight flight) {
        int index = 0;
        for (Flight flight1 : flightList) {
            if (flight1.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
                break;
            }
            index++;
        }
        return index;
    }

    void addNumberOfTicketsToUser(Customer customer, int numOfTickets) {
        customer.numOfTicketsBookedByUser.add(numOfTickets);
    }


    /*overloaded toString() method for displaying all users in a flight....*/
    public String toString(int serialNum, Customer customer) {
        return String.format("| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |", serialNum, customer.getUserID(), customer.getName(),
                customer.getAge(), customer.getEmail(), customer.getAddress(), customer.getPhone());
    }

    public void displayFlightsRegisteredByOneUser(String userID) {
        System.out.println();
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+\n");
        System.out.printf("| Num  | FLIGHT SCHEDULE\t\t\t   | FLIGHT NO |  Booked Tickets  | \tFROM ====>>       | \t====>> TO\t   | \t    ARRIVAL TIME       | FLIGHT TIME |  GATE  |%n");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+\n");
        for (Customer customer : c1.customerCollection) {
            List<Flight> f = customer.getFlightsRegisteredByUser();
            if (userID.equals(customer.getUserID())) {
                for (int i = 0; i < customer.getNumOfFlights(); i++) {
                    System.out.println(toString((i + 1), f.get(i), customer));
                    System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+\n");
                }
            }
        }
    }

    public void displayRegisteredUsersForFlight() {
        for (Flight flight : flight.getFlightList()) {
            List<Customer> c = flight.getCustomersInTheFlight();
            displayEachUser(flight, c);
        }
    }

    private void displayEachUser(Flight flight, List<Customer> c) {
        if (flight.getNumberOfCustomers() != 0) {
            System.out.printf("%40s Displaying Registered Customers for Flight No. \"%-6s\" %s \n\n", "+++++++++++++", flight.getFlightNumber(), "+++++++++++++");
            System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
            System.out.printf("| SerialNum  | UserID\t  | Passenger Names                  | Age     | EmailID\t\t     | Home Address\t\t\t   | Phone Number\t     |%n");
            System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
            for (int i = 0; i < flight.getNumberOfCustomers(); i++) {
                System.out.println(toString((i + 1), c.get(i)));
                System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
            }
            System.out.println("\n");
        }
    }


    /*Cancel a flight registered by the user....*/
    void cancelFlight(String userID, String flightNum) {
        int index = 0, ticketsToBeReturned = 0;
        boolean isFound = false;
        for (Customer customer : c1.customerCollection) {
            if (userID.equals(customer.getUserID())) {
                Iterator<Flight> flightIterator = customer.getFlightsRegisteredByUser().iterator();
                while (flightIterator.hasNext()) {
                    Flight flight = flightIterator.next();
                    if (flightNum.equalsIgnoreCase(flight.getFlightNumber())) {
                        isFound = true;
                        customer.setNumOfFlights(customer.getNumOfFlights() - 1);
                        ticketsToBeReturned = flight.getNoOfSeats() + customer.getNumOfTicketsBookedByUser().get(index);
                        flight.setNoOfSeatsInTheFlight(ticketsToBeReturned);
                        flightIterator.remove();
                        break;
                    }
                    index++;
                }
                if (!isFound) {
                    System.out.println("ERROR!!! Couldn't find Flight with ID \"" + flightNum.toUpperCase() + "\".....");
                }
            }
        }
    }

    public void displayRegisteredUsersForFlight(String flightNum) {
        for (Flight flight : flight.getFlightList()) {
            List<Customer> c = flight.getCustomersInTheFlight();
            if (flight.getFlightNumber().equalsIgnoreCase(flightNum)) {
                displayEachUser(flight, c);
            }
        }
    }
}
