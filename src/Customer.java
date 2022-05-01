import java.util.*;

public class Customer {

    //        ************************************************************ Fields ************************************************************
    private final String userID;
    private String name;
    private String email;
    private String phone;
    private final String password;
    private String address;
    private int age;
    public List<Flight> flightsRegisteredByUser;
    private int numOfFlights;
    public List<Integer> numOfTicketsBookedByUser;
    public final List<Customer> customerCollection = User.getCustomersCollection();

    //        ************************************************************ Behaviours/Methods ************************************************************

    /*  0-Argument constructor of Customer class*/
    Customer() {
        this.userID = null;
        this.name = null;
        this.email = null;
        this.password = null;
        this.phone = null;
        this.address = null;
        this.age = 0;
    }

    Customer(String name, String email, String password, String phone, String address, int age) {
        RandomGenerator random = new RandomGenerator();
        random.randomIDGen();
        this.name = name;
        this.userID = random.getRandomNumber();
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.flightsRegisteredByUser = new ArrayList<>();
        this.numOfTicketsBookedByUser = new ArrayList<>();
        this.numOfFlights = 0;
    }

    void addFlightToCustomerList(Flight f) {
        this.flightsRegisteredByUser.add(f);
        numOfFlights++;
    }

    void addExistingFlightToCustomerList(Flight f, int index,int numOfTickets){
        int newNumOfTickets = numOfTicketsBookedByUser.get(index) + numOfTickets;
        this.numOfTicketsBookedByUser.set(index,newNumOfTickets);
    }

    public void addNewCustomer() {
        System.out.printf("\n\n\n%60s ++++++++++++++ Welcome to the Customer Registration Portal ++++++++++++++", "");
        Customer c1 = new Customer();
        Scanner read = new Scanner(System.in);
        System.out.print("\nEnter your name :\t");
        String name = read.nextLine();
        System.out.print("Enter your email address :\t");
        String email = read.nextLine();
        while (isUniqueData(email)) {
            System.out.println("ERROR!!! User with the same email already exists... Use new email or login using the previous credentials....");
            System.out.print("Enter your email address :\t");
            email = read.nextLine();
        }
        System.out.print("Enter your Password :\t");
        String password = read.nextLine();
        System.out.print("Enter your Phone number :\t");
        String phone = read.nextLine();
        System.out.print("Enter your address :\t");
        String address = read.nextLine();
        System.out.print("Enter your age :\t");
        int age = read.nextInt();
        c1.customerCollection.add(new Customer(name, email, password, phone, address, age));
    }

    private String toString(int i) {
        return String.format("| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |", i, userID, name, age, email, address, phone);
    }

    public void searchUser(String ID) {
        Customer c1 = new Customer();
        boolean isFound = false;
        /*Initializing customerWithTheID to the states of the first obj present in the customerCollection*/
        Customer customerWithTheID = c1.customerCollection.get(0);
        for (Customer c : c1.customerCollection) {
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

    public boolean isUniqueData(String emailID) {
        Customer c1 = new Customer();
        boolean isUnique = false;
        for (Customer c : c1.customerCollection) {
            if (emailID.equals(c.getEmail())) {
                isUnique = true;
                break;
            }
        }
        return isUnique;
    }

    public void editUserInfo(String ID) {
        Customer c1 = new Customer();
        boolean isFound = false;
        Scanner read = new Scanner(System.in);
        for (Customer c : c1.customerCollection) {
            if (ID.equals(c.getUserID())) {
                isFound = true;
                System.out.print("\nEnter the new name of the Passenger:\t");
                c.setName(read.nextLine());
                System.out.print("Enter the new email address of Passenger " + name + ":\t");
                c.setEmail(read.nextLine());
                System.out.print("Enter the new Phone number of Passenger " + name + ":\t");
                c.setPhone(read.nextLine());
                System.out.print("Enter the new address of Passenger " + name + ":\t");
                c.setAddress(read.nextLine());
                System.out.print("Enter the new age of Passenger " + name + ":\t");
                c.setAge(read.nextInt());
                display();
                break;
            }
        }
        if (!isFound) {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", ID);
        }
    }

    public void deleteUser(String ID) {
        Customer c1 = new Customer();
        boolean isFound = false;
        Iterator<Customer> iterator = c1.customerCollection.iterator();
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
            display();
        } else {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", ID);
        }
    }

    public void display() {
        Customer c1 = new Customer();
        Iterator<Customer> iterator = c1.customerCollection.iterator();
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

    //        ************************************************************ Setters & Getters ************************************************************

    public List<Flight> getFlightsRegisteredByUser() {
        return flightsRegisteredByUser;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setNumOfFlights(int numOfFlights) {
        this.numOfFlights = numOfFlights;
    }

    public int getNumOfFlights() {
        return numOfFlights;
    }

    public List<Integer> getNumOfTicketsBookedByUser() {
        return numOfTicketsBookedByUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


