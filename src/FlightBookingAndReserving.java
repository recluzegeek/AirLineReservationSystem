public class FlightBookingAndReserving {
    Customer c1 = new Customer();
    Flight f1 = new Flight();

    void bookFlight(String flightNo, int numOfTickets, String userID) {
        boolean isFound = false;
        for (Flight f1 : f1.getFlightList()) {
            if (flightNo.equalsIgnoreCase(f1.getFlightNumber())) {
                for (Customer customer : c1.customerCollection) {
                    if (userID.equals(customer.getUserID())) {
                        isFound = true;
                        f1.setNoOfSeatsInTheFlight(f1.getNoOfSeats() - numOfTickets);
                        customer.addFlightToUserArray(f1);
                        f1.addCustomerToFlight(customer);
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

    /*toString() Method for displaying number of flights registered by single user...*/
    public String toString(int serialNum, Flight flights) {
        return String.format("| %-5d| %-41s | %-9s | \t%-9s | %-21s | %-22s | %-10s  |   %-6sHrs |  %-4s  |", serialNum, flights.getFlightSchedule(), flights.getFlightNumber(),
                flights.getNoOfSeatsInTheFlight(), flights.getFromWhichCity(), flights.getToWhichCity(), flights.getArrivalTime(), flights.getFlightTime(), flights.getGate());
    }

    /*overloaded toString() method for displaying all users in a flight....*/
    public String toString(int serialNum, Customer customer) {
        return String.format("| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |", serialNum, customer.getUserID(), customer.getName(),
                customer.getAge(), customer.getEmail(), customer.getAddress(), customer.getPhone());
    }

    public void displayRegisteredUsersForFlight() {

        for (Flight flight : f1.getFlightList()) {
            Customer[] c = flight.getCustomersInTheFlight();
            if (flight.getNumberOfCustomers() != 0) {
                System.out.printf("%40s Displaying Registered Customers for Flight No. \"%-6s\" %s \n\n", "+++++++++++++", flight.getFlightNumber(), "+++++++++++++");
                System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
                System.out.printf("| SerialNum  | UserID\t  | Passenger Names                  | Age     | EmailID\t\t     | Home Address\t\t\t   | Phone Number\t     |%n");
                System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");

                for (int i = 0; i < flight.getNumberOfCustomers(); i++) {
                    System.out.println(toString((i + 1), c[i]));
                    System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
                }
                System.out.println("\n");
            }
        }
    }

    public void displayRegisteredUsersForFlight(String flightNum) {

        for (Flight flight : f1.getFlightList()) {
            if (flightNum.equalsIgnoreCase(flight.getFlightNumber())) {
                Customer[] c = flight.getCustomersInTheFlight();
                if (flight.getNumberOfCustomers() != 0) {
                    System.out.printf("\n\n%40s Displaying Registered Customers for Flight No. \"%-6s\" %s \n\n", "+++++++++++++", flight.getFlightNumber(), "+++++++++++++");
                    System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
                    System.out.printf("| SerialNum  | UserID\t  | Passenger Names                  | Age     | EmailID\t\t     | Home Address\t\t\t   | Phone Number\t     |%n");
                    System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
                    for (int i = 0; i < flight.getNumberOfCustomers(); i++) {
                        System.out.println(toString((i + 1), c[i]));
                        System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
                    }
                    System.out.println("\n");
                }
            }
        }
    }

    public void displayFlightsRegisteredByOneUser(String userID) {
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
