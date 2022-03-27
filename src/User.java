/*This class is intended to be the main class for this Project...Here we'll be calling all the necessary methods
 * to perform the first-level implementation of the project like calling the login-method, registering user etc....
 *  */

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {

    private static int countNumOfUsers = 1;
    /*2D Array to store credentials.... Default credentials are stored on 0 index....Max num of users can be 10....*/
    private static String[][] usernameAndPassword = new String[10][2];

    /*Getter method for the 2D Array to be accessed in RolesAndPermission class*/
    public String[][] getUsernameAndPassword() {
        return usernameAndPassword;
    }

    /*Created a List of Customer Class using the name of customersCollection */
    private static List<Customer> customersCollection = new ArrayList<>();

    /*Getter Method for the customersCollection List to be accessed in Customer.java Class..*/
    public static List<Customer> getCustomersCollection() {
        return customersCollection;
    }

    public static void main(String[] args) throws ParseException {
        Flight f1 = new Flight();
        f1.flightScheduler();
//        Main Menu
        Scanner read = new Scanner(System.in);
        System.out.println("\n\t\t\t\t\t+++++++++++++ Welcome to BAV AirLines +++++++++++++\n\nTo Further Proceed, Please enter a value.");
        System.out.println("\n***** Default Username && Password is root-root ***** Using Default Credentials will restrict you to just view the list of Passengers....\n");
        System.out.println("\t\t(a) Press 0 to Exit.");
        System.out.println("\t\t(b) Press 1 to Login.");
        System.out.println("\t\t(c) Press 2 to Register.");
        System.out.println("\t\t(d) Press 3 to display all available Flights.");
        System.out.println("\t\t(e) Press 4 to Reserve a Ticket.");
        System.out.print("\t\tEnter the desired option:    ");
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
                RolesAndPermissions r1 = new RolesAndPermissions();
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
                    System.out.println("You've standard/default privileges to access the data... You can just view customers data..." +
                            "Can't perform any actions on them....");
                    Customer.display();
                } else {
                    System.out.printf("%-20sLogged in Successfully as %s..... For further Proceedings, enter a value from below....", "", username);

                    /*Going to Display the CRUD operations to be performed by the privileged user.....Which includes Creating, Updating
                     * Reading(Searching) and deleting a customer....
                     * */
                    do {
                        System.out.printf("\n%-40s+++++++++ 2nd Layer Menu +++++++++\n", "");
                        System.out.printf("%-30s (a) Enter 1 to add new Passenger....\n", "");
                        System.out.printf("%-30s (b) Enter 2 to search a Passenger....\n", "");
                        System.out.printf("%-30s (c) Enter 3 to update the Data of the Passenger....\n", "");
                        System.out.printf("%-30s (d) Enter 4 to delete a Passenger....\n", "");
                        System.out.printf("%-30s (e) Enter 5 to Display all Passengers....\n", "");
                        System.out.printf("%-30s (f) Enter 0 to Go back to the Main Menu/Logout....\n", "");
                        System.out.print("Enter the desired Choice :   ");
                        desiredOption = read.nextInt();
                        /*If 1 is entered by the privileged user, then add a new customer......*/
                        if (desiredOption == 1) {
                            Customer.addNew();
                        } else if (desiredOption == 2) {
                            /*If 2 is entered by the privileged user, then call the search method of the Customer class*/
                            System.out.print("Enter the CustomerID to Search :\t");
                            String customerID = read1.nextLine();
                            System.out.println();
                            System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
                            System.out.printf("| SerialNum  | UserID\t  | Passenger Name                   | Age     | EmailID\t\t     | Home Address\t\t\t   | Phone Number\t     |%n");
                            System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
                            if (customersCollection.size() > 0) {
                                Customer.searchUser(customerID);
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
                                Customer.editUserInfo(customerID);
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
                                Customer.deleteUser(customerID);
                            } else {
                                System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                            }
                        } else if (desiredOption == 5) {
                            /*Call the Display Method of Customer Class....*/
                            Customer.display();
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
                f1.displayFlightSchedule();
                f1.distanceMeasurementInstructions();
            } else if (desiredOption == 4) {

            } else if (desiredOption == 5) {
            }

            System.out.println("\n\t\t(a) Press 0 to Exit.");
            System.out.println("\t\t(b) Press 1 to Login.");
            System.out.println("\t\t(c) Press 2 to Register.");
            System.out.println("\t\t(d) Press 3 to display all available Flights.");
            System.out.println("\t\t(e) Press 4 to Reserve a Ticket.");
            System.out.print("\n\t\t\t\tEnter the desired option:    ");
            desiredOption = read1.nextInt();
            while (desiredOption < 0 || desiredOption > 8) {
                System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
                desiredOption = read1.nextInt();
            }
        } while (desiredOption != 0);
        System.out.printf("%n%-50sFlying with Trust for Five Decades ...!\n\n\n", "");
    }


}
