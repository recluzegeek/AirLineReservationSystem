import java.util.*;

public class Customer extends User {
    private final String userID;
    private final String name;
    private final String email;
    private final String phone;
    private final String address;
    private final int age;
    private static final List<Customer> customerCollection = User.getCustomersCollection();

    Customer(String name, String email, String phone, String address, int age) {
        RandomGenerator random = new RandomGenerator();
        random.randomIDGen();
        this.name = name;
        this.userID = random.getRandomNumber();
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }

    Customer(String name, String email, String phone, String address, int age, String userID) {
        this.name = name;
        this.userID = userID;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }


    public String getUserID() {
        return userID;
    }

    public static void addNew() {
        Scanner read = new Scanner(System.in);
        System.out.print("\nEnter the name of the Passenger:\t");
        String name = read.nextLine();
        System.out.print("Enter the email address of Passenger " + name + ":\t");
        String email = read.nextLine();
        System.out.print("Enter the Phone number of Passenger " + name + ":\t");
        String phone = read.nextLine();
        System.out.print("Enter the address of Passenger " + name + ":\t");
        String address = read.nextLine();
        System.out.print("Enter the age of Passenger " + name + ":\t");
        int age = read.nextInt();
        customerCollection.add(new Customer(name, email, phone, address, age));
    }

    private String toString(int i) {
        return String.format("| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |", i, userID, name, age, email, address, phone);
    }

    public static void searchUser(String ID) {
        boolean isFound = false;
        /*Initializing customerWithTheID to the states of the first obj present in the customerCollection*/
        Customer customerWithTheID = customerCollection.get(0);
        for (Customer c : customerCollection) {
            if (ID.equals(c.getUserID())) {
                isFound = true;
                customerWithTheID = c;
                break;
            }
        }
        if (isFound) {
            System.out.printf("%-50sCustomer Found...!!!Here is the Full Record...!!!\n\n\n", " ");
            System.out.println(customerWithTheID.toString(1));
        } else {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", ID);
        }
    }

    public static void editUserInfo(String ID) {
        boolean isFound = false;
//        Customer customerWithTheID = customerCollection.get(0);
        Scanner read = new Scanner(System.in);
        ListIterator<Customer> listIterator = customerCollection.listIterator();
        while (listIterator.hasNext()) {
            Customer c = listIterator.next();
            if (ID.equals(c.getUserID())) {
                isFound = true;
                break;
            }
        }
        if (isFound) {
            System.out.print("\nEnter the new name of the Passenger:\t");
            String name = read.nextLine();
            System.out.print("Enter the new email address of Passenger " + name + ":\t");
            String email = read.nextLine();
            System.out.print("Enter the new Phone number of Passenger " + name + ":\t");
            String phone = read.nextLine();
            System.out.print("Enter the new address of Passenger " + name + ":\t");
            String address = read.nextLine();
            System.out.print("Enter the new age of Passenger " + name + ":\t");
            int age = read.nextInt();
            listIterator.set(new Customer(name, email, phone, address, age, ID));
            display();
        } else {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", ID);
        }
    }

    public static void deleteUser(String ID) {
        boolean isFound = false;
        Iterator<Customer> iterator = customerCollection.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (ID.equals(customer.getUserID())) {
                isFound = true;
                break;
            }
        }
        if (isFound) {
            iterator.remove();
            System.out.printf("\n%-50sPrinting all  Customer's Data after deleting Customer with the ID %s.....!!!!\n", "", ID);
            Customer.display();
        } else {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", ID);
        }
//        customerCollection.removeIf(customer -> ID.equals(customer.getUserID()));
    }

    public static void display() {
        Iterator<Customer> iterator = customerCollection.iterator();
        System.out.println();
        System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
        System.out.printf("| SerialNum  | UserID\t  | Passenger Names                  | Age     | EmailID\t\t     | Home Address\t\t\t   | Phone Number\t     |%n");
        System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
        System.out.println();
        int i = 0;
        while (iterator.hasNext()) {
            i++;
            Customer c = iterator.next();
            System.out.println(c.toString(i));
            System.out.print("+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n");
        }
    }
}


