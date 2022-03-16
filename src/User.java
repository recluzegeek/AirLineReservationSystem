import java.util.Scanner;

public class User {
    //    public String name;
//    public String email;
//    public String phone;
//    public String address;
//    public int age;
    private static int numberOfUsers = 0;

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("\t\t\t\t\t\t\t\t\t+++++++++++++ Welcome to BAV AirLines +++++++++++++\nTo Further Proceed, Please enter a value. ");
        System.out.println("\t\t(a) Press 0 to Exit.");
        System.out.println("\t\t(b) Press 1 to Login.");
        System.out.println("\t\t(c) Press 2 to Register.");
        System.out.println("\t\t(d) Press 3 to Book a Ticket.");
        System.out.println("\t\t(e) Press 4 to Reserve a Ticket.\t\t\t\t\t\t*****Default Username && Password is root-root*****");
        System.out.print("\t\tEnter the desired option:    ");
        int desiredOption = read.nextInt();
        while (desiredOption < 0 || desiredOption > 4) {
            System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
            desiredOption = read.nextInt();
        }
        String[][] arr = new String[10][5];
        boolean continueProgram = true;
        while (continueProgram) {
            if (desiredOption == 0) {
                continueProgram = false;
            } else if (desiredOption == 1) {
                boolean check = addNew(arr, numberOfUsers);
                if (check) {
                    numberOfUsers++;
//                    System.out.println("Number of Users are : " + numberOfUsers);
                } else {
                    System.out.println("Number of Maximum Users Reached...!! Can't add more...Re-run the program...Thanks");
                }
                listOfAllPassengers(arr);
            }
        }


    }

    public static boolean addNew(String[][] arr, int currentUser) {
        Scanner read = new Scanner(System.in);
        if (currentUser < arr.length) {
            System.out.print("\nEnter the name of the Passenger:\t");
            arr[currentUser][0] = read.nextLine();
            System.out.print("Enter the email :\t");
            arr[currentUser][1] = read.nextLine();
            System.out.print("Enter the Phone number of Passenger " + arr[currentUser][0] + ":\t");
            arr[currentUser][2] = read.nextLine();
            System.out.print("Enter the address of Passenger " + arr[currentUser][0] + ":\t");
            arr[currentUser][3] = read.nextLine();
            System.out.print("Enter the age of Passenger " + arr[currentUser][0] + ":\t");
            arr[currentUser][4] = read.nextLine();
            return true;
        } else {
            return false;
        }
    }

    static void listOfAllPassengers(String[][] passengersList) {
        System.out.println();
        System.out.print("+------------+----------------------------------+----------+-----------------------------+-------------------------------------+-------------------------+\n");
        System.out.printf("| Serial No. | Passenger Names                  | Age\t   |   EmailID\t\t\t\t     | Home Address\t\t\t\t\t\t   | Phone Number\t\t     |%n");
        System.out.print("+------------+----------------------------------+----------+-----------------------------+-------------------------------------+-------------------------+\n");
        for (int i = 0; i < passengersList.length; i++) {
            int count = 0;
            System.out.println();
            for (int j = 0; j < 6; j++) {
                if (count == 0) {
                    System.out.printf("| %-10d ", (i + 1));
                } else if (count == 1) {
                    System.out.printf("| %-32s |  ", passengersList[i][0]);
                } else if (count == 2) {
                    System.out.printf("%-7s |  ", passengersList[i][4]);
                } else if (count == 3) {
                    System.out.printf("%-26s |  ", passengersList[i][1]);
                } else if (count == 4) {
                    System.out.printf(" %-32s  |  ", passengersList[i][3]);
                } else {
                    System.out.printf(" %-20s  |  ", passengersList[i][2]);
                }
                count++;
            }
            System.out.print("\n+------------+----------------------------------+----------+-----------------------------+-------------------------------------+-------------------------+");
        }
    }


    public void edit() {

    }

    public void update() {

    }

    public void delete() {

    }
}
