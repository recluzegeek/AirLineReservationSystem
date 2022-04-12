/*This class is intended to be the main class for this Project...Here we'll be calling all the necessary methods
 * to perform the first-level implementation of the project like calling the login-method, registering user etc....
 *  */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {

    private static int countNumOfUsers = 1;
    /*2D Array to store credentials.... Default credentials are stored on 0 index....Max num of users can be 10....*/
    private static final String[][] usernameAndPassword = new String[10][2];

//    public final List<Flight> flightsRegisteredByCustomer = new ArrayList<>();

//
//    public List<Flight> getFlightsRegisteredByCustomer() {
//        return flightsRegisteredByCustomer;
//    }

    /*Getter method for the 2D Array to be accessed in RolesAndPermission class*/
    public String[][] getUsernameAndPassword() {
        return usernameAndPassword;
    }

    /*Created a List of Customer Class using the name of customersCollection */
    public static final List<Customer> customersCollection = new ArrayList<>();

    /*Getter Method for the customersCollection List to be accessed in Customer.java Class..*/
    public static List<Customer> getCustomersCollection() {
        return customersCollection;
    }

    public static void main(String[] args) {
        RolesAndPermissions r1 = new RolesAndPermissions();
        Flight f1 = new Flight();
        FlightBookingAndReserving bookingAndReserving = new FlightBookingAndReserving();
        Customer c1 = new Customer();
        f1.flightScheduler();
//        Main Menu
        Scanner read = new Scanner(System.in);
        System.out.println("\n\t\t\t\t\t+++++++++++++ Welcome to BAV AirLines +++++++++++++\n\nTo Further Proceed, Please enter a value.");
        System.out.println("\n***** Default Username && Password is root-root ***** Using Default Credentials will restrict you to just view the list of Passengers....\n");
        displayMenu();
        int desiredOption = read.nextInt();
        /*Doing Input-Validation*/
        while (desiredOption < 0 || desiredOption > 8) {
            System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
            desiredOption = read.nextInt();
        }
        /*Do-while loop to iterate or ask the option continuously until user enter 0 */
        do {
            Scanner read1 = new Scanner(System.in);
            /* If desiredOption is 1 then call the login method.... if default credentials are used then set the permission
             * level to standard/default where the user can just view the customer's data...if not found, then return -1, and if
             * data is found then show the user display menu for adding, updating, deleting and searching users/customers...
             * */
            if (desiredOption == 1) {

                /*Setting the default username and password....*/
                usernameAndPassword[0][0] = "root";
                usernameAndPassword[0][1] = "root";

                /*Asking for the usernames && passwords....*/
                System.out.print("\nEnter the UserName to login to the Management System :     ");
                String username = read1.nextLine();
                System.out.print("Enter the Password to login to the Management  System :    ");
                String password = read1.nextLine();
                System.out.println();

                /*Checking the RolesAndPermissions......*/
                if (r1.isPrivilegedUserOrNot(username, password) == -1) {
                    System.out.println("ERROR!!! Cannot find user with the entered credentials.... Try Creating New Credentials or get yourself register by pressing 2....");
                } else if (r1.isPrivilegedUserOrNot(username, password) == 0) {
                    System.out.println("You've standard/default privileges to access the data... You can just view customers data..." + "Can't perform any actions on them....");
                    c1.display();
                } else {
                    System.out.printf("%-20sLogged in Successfully as %s..... For further Proceedings, enter a value from below....", "", username);

                    /*Going to Display the CRUD operations to be performed by the privileged user.....Which includes Creating, Updating
                     * Reading(Searching) and deleting a customer....
                     * */
                    do {
                        display2ndLayerMenu();
                        desiredOption = read.nextInt();
                        /*If 1 is entered by the privileged user, then add a new customer......*/
                        if (desiredOption == 1) {
                            c1.addNew();
                        } else if (desiredOption == 2) {
                            /*If 2 is entered by the privileged user, then call the search method of the Customer class*/
                            System.out.print("Enter the CustomerID to Search :\t");
                            String customerID = read1.nextLine();
                            System.out.println();
                            System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
                            System.out.printf("| SerialNum  | UserID\t  | Passenger Name                   | Age     | EmailID\t\t     | Home Address\t\t\t   | Phone Number\t     |%n");
                            System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
                            if (customersCollection.size() > 0) {
                                c1.searchUser(customerID);
                            } else {
                                System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                            }
                            System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
                        } else if (desiredOption == 3) {
                            /*If 3 is entered by the user, then call the update method of the Customer Class with required
                             * arguments.....
                             * */
                            System.out.print("Enter the CustomerID to Update its Data :\t");
                            String customerID = read1.nextLine();
                            if (customersCollection.size() > 0) {
                                c1.editUserInfo(customerID);
                            } else {
                                System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                            }

                        } else if (desiredOption == 4) {
                            /*If 4 is entered, then ask the user to enter the customer id, and then delete
                             * that customer....
                             * */
                            System.out.print("Enter the CustomerID to Delete its Data :\t");
                            String customerID = read1.nextLine();
                            if (customersCollection.size() > 0) {
                                c1.deleteUser(customerID);
                            } else {
                                System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                            }
                        } else if (desiredOption == 5) {
                            /*Call the Display Method of Customer Class....*/
                            c1.display();
                        } else if (desiredOption == 6) {
                            System.out.print("Do you want to display Passengers of all flights or a specific flight.... 'Y/y' for displaying all flights and 'N/n' to look for a" +
                                    " specific flight.... ");
                            char choice = read1.nextLine().charAt(0);
                            if ('y' == choice || 'Y' == choice) {
                                bookingAndReserving.displayRegisteredUsersForFlight();
                            } else if ('n' == choice || 'N' == choice) {
                                f1.displayFlightSchedule();
                                System.out.print("Enter the Flight Number to display the list of passengers registered in that flight... ");
                                String flightNum = read1.nextLine();
                                bookingAndReserving.displayRegisteredUsersForFlight(flightNum);
                            } else {
                                System.out.println("Invalid Choice...No Response...!");
                            }
                        } else if (desiredOption == 0) {
                            System.out.println("Thanks for Using BAV Airlines Ticketing System...!!!");
                        } else {
                            System.out.println("Invalid Choice...Looks like you're Robot...Entering values randomly...You've Have to login again...");
                            desiredOption = 0;
                        }

                    } while (desiredOption != 0);

                }
            } else if (desiredOption == 2) {

                /*If desiredOption is 2, then call the registration method to register a user......*/
                System.out.print("\nEnter the UserName to Register :    ");
                String username = read1.nextLine();
                System.out.print("Enter the Password to Register :     ");
                String password = read1.nextLine();

                /*Setting the credentials entered by the user.....*/
                usernameAndPassword[countNumOfUsers][0] = username;
                usernameAndPassword[countNumOfUsers][1] = password;

                /*Incrementing the numOfUsers */
                countNumOfUsers++;
            } else if (desiredOption == 3) {
                System.out.print("\n\nEnter the UserName to Login : \t");
                String userName = read1.nextLine();
                System.out.print("Enter the Password : \t");
                String password = read1.nextLine();
                String[] result = r1.isPassengerRegistered(userName, password).split("-");

                if (Integer.parseInt(result[0]) == 1) {
                    int desiredChoice = 1;
                    System.out.printf("\n\n%-20sLogged in Successfully as %s..... For further Proceedings, enter a value from below....", "", userName);
                    do {
                        display3rdLayerMenu(userName);
                        desiredChoice = read.nextInt();
                        if (desiredChoice == 1) {
                            FlightBookingAndReserving flightBookingAndReserving = new FlightBookingAndReserving();
                            f1.displayFlightSchedule();
                            System.out.print("\nEnter the desired flight number to book :\t ");
                            String flightToBeBooked = read1.nextLine();
                            System.out.print("Enter the Number of tickets for " + flightToBeBooked + " flight :   ");
                            int numOfTickets = read.nextInt();
                            while (numOfTickets > 10) {
                                System.out.print("ERROR!! You can't book more than 10 tickets at a time for single flight....Enter number of tickets again : s");
                                numOfTickets = read.nextInt();
                            }
                            flightBookingAndReserving.bookFlight(flightToBeBooked, numOfTickets, result[1]);
                        } else if (desiredChoice == 2) {
                            c1.editUserInfo(result[1]);
                        } else if (desiredChoice == 3) {
                            c1.deleteUser(result[1]);
                            System.out.printf("User %s's account deleted Successfully...!!!", userName);
                            desiredChoice = 0;
                        } else if (desiredChoice == 4) {
                            f1.displayFlightSchedule();
                            f1.distanceMeasurementInstructions();
                        } else if (desiredChoice == 5) {
                            bookingAndReserving.displayFlightsRegisteredByOneUser(result[1]);
                        } else {
                            if (desiredChoice != 0) {
                                System.out.println("Invalid Choice...Looks like you're Robot...Entering values randomly...You've Have to login again...");
                            }
                            desiredChoice = 0;
                        }
                    } while (desiredChoice != 0);

                } else {
                    System.out.println("Unable to Login");
                }
            } else if (desiredOption == 4) {
                c1.addNew();
            }

            displayMenu();
            desiredOption = read1.nextInt();
            while (desiredOption < 0 || desiredOption > 8) {
                System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
                desiredOption = read1.nextInt();
            }
        } while (desiredOption != 0);
        System.out.printf("%n%-50sFlying with Trust for Five Decades ...!\n\n\n", "");
    }


    static void display3rdLayerMenu(String name) {
        System.out.printf("\n\n%-60s+++++++++ 3rd Layer Menu +++++++++%50sLogged in as %s\n", "", "", name);
        System.out.printf("%-40s (a) Enter 1 to Book a flight....\n", "");
        System.out.printf("%-40s (b) Enter 2 to update your Data....\n", "");
        System.out.printf("%-40s (c) Enter 3 to delete your account....\n", "");
        System.out.printf("%-40s (d) Enter 4 to Display Flight Schedule....\n", "");
        System.out.printf("%-40s (e) Enter 5 to Display all flights registered by \"%s\"....\n", "", name);
        System.out.printf("%-40s (f) Enter 0 to Go back to the Main Menu/Logout....\n", "");
        System.out.print("Enter the desired Choice :   ");

    }

    static void display2ndLayerMenu() {
        System.out.printf("\n%-40s+++++++++ 2nd Layer Menu +++++++++\n", "");
        System.out.printf("%-30s (a) Enter 1 to add new Passenger....\n", "");
        System.out.printf("%-30s (b) Enter 2 to search a Passenger....\n", "");
        System.out.printf("%-30s (c) Enter 3 to update the Data of the Passenger....\n", "");
        System.out.printf("%-30s (d) Enter 4 to delete a Passenger....\n", "");
        System.out.printf("%-30s (e) Enter 5 to Display all Passengers....\n", "");
        System.out.printf("%-30s (f) Enter 6 to Display all Registered Passengers in a Flight....\n", "");
        System.out.printf("%-30s (g) Enter 0 to Go back to the Main Menu/Logout....\n", "");
        System.out.print("Enter the desired Choice :   ");
    }


    static void displayMenu() {
        System.out.println("\n\n\t\t(a) Press 0 to Exit.");
        System.out.println("\t\t(b) Press 1 to Login as admin.");
        System.out.println("\t\t(c) Press 2 to Register as admin.");
        System.out.println("\t\t(b) Press 3 to Login as Passenger.");
        System.out.println("\t\t(c) Press 4 to Register as Passenger.");
        System.out.print("\t\tEnter the desired option:    ");
    }
}
