/*
 * FlightReservation class allows the user to book, cancel and check the status of the registered flights.
 *
 *
 * */


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FlightReservation implements DisplayClass {

    //        ************************************************************ Fields ************************************************************
    Flight flight = new Flight();
    int flightIndexInFlightList;

    //        ************************************************************ Behaviours/Methods ************************************************************


    /**
     * Book the numOfTickets for said flight for the specified user. Update the available seats in main system by
     * Subtracting the numOfTickets from the main system. If a new customer registers for the flight, then it adds
     * the customer to that flight, else if the user is already added to that flight, then it just updates the
     * numOfSeats of that flight.
     *
     * @param flightNo     FlightID of the flight to be booked
     * @param numOfTickets number of tickets to be booked
     * @param userID       userID of the user which is booking the flight
     */
    void bookFlight(String flightNo, int numOfTickets, String userID) {
        boolean isFound = false;
        for (Flight f1 : flight.getFlightList()) {
            if (flightNo.equalsIgnoreCase(f1.getFlightNumber())) {
                for (Customer customer : Customer.customerCollection) {
                    if (userID.equals(customer.getUserID())) {
                        isFound = true;
                        f1.setNoOfSeatsInTheFlight(f1.getNoOfSeats() - numOfTickets);
                        if (!f1.isCustomerAlreadyAdded(f1.getListOfRegisteredCustomersInAFlight(), customer)) {
                            f1.addNewCustomerToFlight(customer);
                        }
                        if (isFlightAlreadyAddedToCustomerList(customer.flightsRegisteredByUser, f1)) {
                            addNumberOfTicketsToAlreadyBookedFlight(customer, numOfTickets);
                            if (flightIndex(flight.getFlightList(), flight) != -1) {
                                customer.addExistingFlightToCustomerList(flightIndex(flight.getFlightList(), flight), numOfTickets);
                            }
                        } else {
                            customer.addNewFlightToCustomerList(f1);
                            addNumberOfTicketsForNewFlight(customer, numOfTickets);
                        }
                    break;
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

    /**
     * Cancels the flight for a particular user and return/add the numOfTickets back to
     * the main flight scheduler.
     *
     * @param userID    ID of the user for whom the flight is to be cancelled
     */
    void cancelFlight(String userID) {
        String flightNum = "";
        Scanner read = new Scanner(System.in);
        int index = 0, ticketsToBeReturned;
        boolean isFound = false;
        for (Customer customer : Customer.customerCollection) {
            if (userID.equals(customer.getUserID())) {
                if (customer.getFlightsRegisteredByUser().size() != 0) {
                    System.out.printf("%50s %s Here is the list of all the Flights registered by you %s", " ", "++++++++++++++", "++++++++++++++");
                    displayFlightsRegisteredByOneUser(userID);
                    System.out.print("Enter the Flight Number of the Flight you want to cancel : ");
                    flightNum = read.nextLine();
                    System.out.print("Enter the number of tickets to cancel : ");
                    int numOfTickets = read.nextInt();
                    Iterator<Flight> flightIterator = customer.getFlightsRegisteredByUser().iterator();
                    while (flightIterator.hasNext()) {
                        Flight flight = flightIterator.next();
                        if (flightNum.equalsIgnoreCase(flight.getFlightNumber())) {
                            isFound = true;
                            int numOfTicketsForFlight = customer.getNumOfTicketsBookedByUser().get(index);
                            while (numOfTickets > numOfTicketsForFlight) {
                                System.out.print("ERROR!!! Number of tickets cannot be greater than " + numOfTicketsForFlight + " for this flight. Please enter the number of tickets again : ");
                                numOfTickets = read.nextInt();
                            }
                            if (numOfTicketsForFlight == numOfTickets) {
                                ticketsToBeReturned = flight.getNoOfSeats() + numOfTicketsForFlight;
                                customer.numOfTicketsBookedByUser.remove(index);
                                flightIterator.remove();
                            } else {
                                ticketsToBeReturned = numOfTickets + flight.getNoOfSeats();
                                customer.numOfTicketsBookedByUser.set(index, (numOfTicketsForFlight - numOfTickets));
                            }
                            flight.setNoOfSeatsInTheFlight(ticketsToBeReturned);
                            break;
                        }
                        index++;
                    }

                }else{
                    System.out.println("No Flight Has been Registered by you with ID \"\"" + flightNum.toUpperCase() +"\"\".....");
                }
//                index++;
                if (!isFound) {
                    System.out.println("ERROR!!! Couldn't find Flight with ID \"" + flightNum.toUpperCase() + "\".....");
                }
            }
        }
    }

    void addNumberOfTicketsToAlreadyBookedFlight(Customer customer, int numOfTickets) {
        int newNumOfTickets = customer.numOfTicketsBookedByUser.get(flightIndexInFlightList) + numOfTickets;
        customer.numOfTicketsBookedByUser.set(flightIndexInFlightList, newNumOfTickets);
    }

    void addNumberOfTicketsForNewFlight(Customer customer, int numOfTickets) {
        customer.numOfTicketsBookedByUser.add(numOfTickets);
    }

    boolean isFlightAlreadyAddedToCustomerList(List<Flight> flightList, Flight flight) {
        boolean addedOrNot = false;
        for (Flight flight1 : flightList) {
            if (flight1.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
                this.flightIndexInFlightList = flightList.indexOf(flight1);
                addedOrNot = true;
                break;
            }
        }
        return addedOrNot;
    }

    String flightStatus(Flight flight) {
        boolean isFlightAvailable = false;
        for (Flight list : flight.getFlightList()) {
            if (list.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
                isFlightAvailable = true;
                break;
            }
        }
        if (isFlightAvailable) {
            return "As Per Schedule";
        } else {
            return "   Cancelled   ";
        }
    }

    /*toString() Method for displaying number of flights registered by single user...*/
    public String toString(int serialNum, Flight flights, Customer customer) {
        return String.format("| %-5d| %-41s | %-9s | \t%-9d | %-21s | %-22s | %-10s  |   %-6sHrs |  %-4s  | %-10s |", serialNum, flights.getFlightSchedule(), flights.getFlightNumber(), customer.numOfTicketsBookedByUser.get(serialNum - 1), flights.getFromWhichCity(), flights.getToWhichCity(), flights.fetchArrivalTime(), flights.getFlightTime(), flights.getGate(), flightStatus(flights));
    }

    @Override
    public void displayFlightsRegisteredByOneUser(String userID) {
        System.out.println();
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
        System.out.printf("| Num  | FLIGHT SCHEDULE\t\t\t   | FLIGHT NO |  Booked Tickets  | \tFROM ====>>       | \t====>> TO\t   | \t    ARRIVAL TIME       | FLIGHT TIME |  GATE  |  FLIGHT STATUS  |%n");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
        for (Customer customer : Customer.customerCollection) {
            List<Flight> f = customer.getFlightsRegisteredByUser();
            int size = customer.getFlightsRegisteredByUser().size();
            if (userID.equals(customer.getUserID())) {
                for (int i = 0; i < size; i++) {
                    System.out.println(toString((i + 1), f.get(i), customer));
                    System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
                }
            }
        }
    }

    /*overloaded toString() method for displaying all users in a flight....*/

    public String toString(int serialNum, Customer customer, int index) {
        return String.format("%10s| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |       %-7s  |", "", (serialNum + 1), customer.randomIDDisplay(customer.getUserID()), customer.getName(),
                customer.getAge(), customer.getEmail(), customer.getAddress(), customer.getPhone(), customer.numOfTicketsBookedByUser.get(index));
    }

    @Override
    public void displayHeaderForUsers(Flight flight, List<Customer> c) {
        System.out.printf("\n%65s Displaying Registered Customers for Flight No. \"%-6s\" %s \n\n", "+++++++++++++", flight.getFlightNumber(), "+++++++++++++");
        System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+----------------+\n", "");
        System.out.printf("%10s| SerialNum  |   UserID   | Passenger Names                  | Age     | EmailID\t\t       | Home Address\t\t\t     | Phone Number\t       | Booked Tickets |%n", "");
        System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+----------------+\n", "");
        int size = flight.getListOfRegisteredCustomersInAFlight().size();
        for (int i = 0; i < size; i++) {
            System.out.println(toString(i, c.get(i), flightIndex(c.get(i).flightsRegisteredByUser, flight)));
            System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+----------------+\n", "");
        }
    }

    @Override
    public void displayRegisteredUsersForAllFlight() {
        System.out.println();
        for (Flight flight : flight.getFlightList()) {
            List<Customer> c = flight.getListOfRegisteredCustomersInAFlight();
            int size = flight.getListOfRegisteredCustomersInAFlight().size();
            if (size != 0) {
                displayHeaderForUsers(flight, c);
            }
        }
    }

    int flightIndex(List<Flight> flightList, Flight flight) {
        int i = -1;
        for (Flight flight1 : flightList) {
            if (flight1.equals(flight)) {
                i = flightList.indexOf(flight1);
            }
        }
        return i;
    }

    @Override
    public void displayRegisteredUsersForASpecificFlight(String flightNum) {
        System.out.println();
        for (Flight flight : flight.getFlightList()) {
            List<Customer> c = flight.getListOfRegisteredCustomersInAFlight();
            if (flight.getFlightNumber().equalsIgnoreCase(flightNum)) {
                displayHeaderForUsers(flight, c);
            }
        }
    }

    @Override
    public void displayArtWork(int option) {
        String artWork;
        if (option == 1) {
            artWork = """
                                        
                    d8888b.  .d88b.   .d88b.  db   dD      d88888b db      d888888b  d888b  db   db d888888b\s
                    88  `8D .8P  Y8. .8P  Y8. 88 ,8P'      88'     88        `88'   88' Y8b 88   88 `~~88~~'\s
                    88oooY' 88    88 88    88 88,8P        88ooo   88         88    88      88ooo88    88   \s
                    88~~~b. 88    88 88    88 88`8b        88~~~   88         88    88  ooo 88~~~88    88   \s
                    88   8D `8b  d8' `8b  d8' 88 `88.      88      88booo.   .88.   88. ~8~ 88   88    88   \s
                    Y8888P'  `Y88P'   `Y88P'  YP   YD      YP      Y88888P Y888888P  Y888P  YP   YP    YP   \s
                                                                                                            \s
                                                                                                            \s
                    """;
        } else if (option == 2) {
            artWork = """
                                        
                    d88888b d8888b. d888888b d888888b      d888888b d8b   db d88888b  .d88b. \s
                    88'     88  `8D   `88'   `~~88~~'        `88'   888o  88 88'     .8P  Y8.\s
                    88ooooo 88   88    88       88            88    88V8o 88 88ooo   88    88\s
                    88~~~~~ 88   88    88       88            88    88 V8o88 88~~~   88    88\s
                    88.     88  .8D   .88.      88           .88.   88  V888 88      `8b  d8'\s
                    Y88888P Y8888D' Y888888P    YP         Y888888P VP   V8P YP       `Y88P' \s
                                                                                             \s
                                                                                             \s
                    """;
        } else if (option == 3) {
            artWork = """
                                        
                    d8888b. d88888b db      d88888b d888888b d88888b       .d8b.   .o88b.  .o88b.  .d88b.  db    db d8b   db d888888b\s
                    88  `8D 88'     88      88'     `~~88~~' 88'          d8' `8b d8P  Y8 d8P  Y8 .8P  Y8. 88    88 888o  88 `~~88~~'\s
                    88   88 88ooooo 88      88ooooo    88    88ooooo      88ooo88 8P      8P      88    88 88    88 88V8o 88    88   \s
                    88   88 88~~~~~ 88      88~~~~~    88    88~~~~~      88~~~88 8b      8b      88    88 88    88 88 V8o88    88   \s
                    88  .8D 88.     88booo. 88.        88    88.          88   88 Y8b  d8 Y8b  d8 `8b  d8' 88b  d88 88  V888    88   \s
                    Y8888D' Y88888P Y88888P Y88888P    YP    Y88888P      YP   YP  `Y88P'  `Y88P'  `Y88P'  ~Y8888P' VP   V8P    YP   \s
                                                                                                                                     \s
                                                                                                                                     \s
                    """;
        } else if (option == 4) {
            artWork = """

                    d8888b.  .d8b.  d8b   db d8888b.  .d88b.  .88b  d88.      d88888b db      d888888b  d888b  db   db d888888b      .d8888.  .o88b. db   db d88888b d8888b. db    db db      d88888b\s
                    88  `8D d8' `8b 888o  88 88  `8D .8P  Y8. 88'YbdP`88      88'     88        `88'   88' Y8b 88   88 `~~88~~'      88'  YP d8P  Y8 88   88 88'     88  `8D 88    88 88      88'    \s
                    88oobY' 88ooo88 88V8o 88 88   88 88    88 88  88  88      88ooo   88         88    88      88ooo88    88         `8bo.   8P      88ooo88 88ooooo 88   88 88    88 88      88ooooo\s
                    88`8b   88~~~88 88 V8o88 88   88 88    88 88  88  88      88~~~   88         88    88  ooo 88~~~88    88           `Y8b. 8b      88~~~88 88~~~~~ 88   88 88    88 88      88~~~~~\s
                    88 `88. 88   88 88  V888 88  .8D `8b  d8' 88  88  88      88      88booo.   .88.   88. ~8~ 88   88    88         db   8D Y8b  d8 88   88 88.     88  .8D 88b  d88 88booo. 88.    \s
                    88   YD YP   YP VP   V8P Y8888D'  `Y88P'  YP  YP  YP      YP      Y88888P Y888888P  Y888P  YP   YP    YP         `8888Y'  `Y88P' YP   YP Y88888P Y8888D' ~Y8888P' Y88888P Y88888P\s
                                                                                                                                                                                                     \s
                    Are you thinking that it's not feasible(RandomSchedule)...Then you can confirm it by re-running the program...
                    """;
        } else if (option == 5) {
            artWork = """
                                        
                     .o88b.  .d8b.  d8b   db  .o88b. d88888b db           d88888b db      d888888b  d888b  db   db d888888b\s
                    d8P  Y8 d8' `8b 888o  88 d8P  Y8 88'     88           88'     88        `88'   88' Y8b 88   88 `~~88~~'\s
                    8P      88ooo88 88V8o 88 8P      88ooooo 88           88ooo   88         88    88      88ooo88    88   \s
                    8b      88~~~88 88 V8o88 8b      88~~~~~ 88           88~~~   88         88    88  ooo 88~~~88    88   \s
                    Y8b  d8 88   88 88  V888 Y8b  d8 88.     88booo.      88      88booo.   .88.   88. ~8~ 88   88    88   \s
                     `Y88P' YP   YP VP   V8P  `Y88P' Y88888P Y88888P      YP      Y88888P Y888888P  Y888P  YP   YP    YP   \s
                                                                                                                           \s
                                                                                                                           \s
                    """;
        } else if (option == 6) {
            artWork = """
                                        
                    d8888b. d88888b  d888b  d888888b .d8888. d888888b d88888b d8888b. d88888b d8888b.      d88888b db      d888888b  d888b  db   db d888888b .d8888.     \s
                    88  `8D 88'     88' Y8b   `88'   88'  YP `~~88~~' 88'     88  `8D 88'     88  `8D      88'     88        `88'   88' Y8b 88   88 `~~88~~' 88'  YP     \s
                    88oobY' 88ooooo 88         88    `8bo.      88    88ooooo 88oobY' 88ooooo 88   88      88ooo   88         88    88      88ooo88    88    `8bo.       \s
                    88`8b   88~~~~~ 88  ooo    88      `Y8b.    88    88~~~~~ 88`8b   88~~~~~ 88   88      88~~~   88         88    88  ooo 88~~~88    88      `Y8b.     \s
                    88 `88. 88.     88. ~8~   .88.   db   8D    88    88.     88 `88. 88.     88  .8D      88      88booo.   .88.   88. ~8~ 88   88    88    db   8D     \s
                    88   YD Y88888P  Y888P  Y888888P `8888Y'    YP    Y88888P 88   YD Y88888P Y8888D'      YP      Y88888P Y888888P  Y888P  YP   YP    YP    `8888Y'     \s
                                                                                                                                                                         \s
                                                                                                                                                                         \s
                    d8888b. db    db      d8888b.  .d8b.  .d8888. .d8888. d88888b d8b   db  d888b  d88888b d8888b.                                                       \s
                    88  `8D `8b  d8'      88  `8D d8' `8b 88'  YP 88'  YP 88'     888o  88 88' Y8b 88'     88  `8D                                                       \s
                    88oooY'  `8bd8'       88oodD' 88ooo88 `8bo.   `8bo.   88ooooo 88V8o 88 88      88ooooo 88oobY'                                                       \s
                    88~~~b.    88         88~~~   88~~~88   `Y8b.   `Y8b. 88~~~~~ 88 V8o88 88  ooo 88~~~~~ 88`8b                                                         \s
                    88   8D    88         88      88   88 db   8D db   8D 88.     88  V888 88. ~8~ 88.     88 `88.                                                       \s
                    Y8888P'    YP         88      YP   YP `8888Y' `8888Y' Y88888P VP   V8P  Y888P  Y88888P 88   YD                                                       \s
                                                                                                                                                                         \s
                                                                                                                                                                         \s
                    """;
        } else {
            artWork = """

                    db       .d88b.   d888b   d888b  d88888b d8888b.       .d88b.  db    db d888888b\s
                    88      .8P  Y8. 88' Y8b 88' Y8b 88'     88  `8D      .8P  Y8. 88    88 `~~88~~'\s
                    88      88    88 88      88      88ooooo 88   88      88    88 88    88    88   \s
                    88      88    88 88  ooo 88  ooo 88~~~~~ 88   88      88    88 88    88    88   \s
                    88booo. `8b  d8' 88. ~8~ 88. ~8~ 88.     88  .8D      `8b  d8' 88b  d88    88   \s
                    Y88888P  `Y88P'   Y888P   Y888P  Y88888P Y8888D'       `Y88P'  ~Y8888P'    YP   \s
                                                                                                    \s
                                                                                                    \s
                    """;
        }

        System.out.println(artWork);
    }
}
