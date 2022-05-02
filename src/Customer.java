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

    void addExistingFlightToCustomerList(int index, int numOfTickets) {
        int newNumOfTickets = numOfTicketsBookedByUser.get(index) + numOfTickets;
        this.numOfTicketsBookedByUser.set(index, newNumOfTickets);
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
        return String.format("%10s| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |", "", i, randomIDDisplay(userID), name, age, email, address, phone);
    }

    public void searchUser(String ID) {
        boolean isFound = false;
        Customer customerWithTheID = customerCollection.get(0);
        for (Customer c : customerCollection) {
            if (ID.equals(c.getUserID())) {
                System.out.printf("%-50sCustomer Found...!!!Here is the Full Record...!!!\n\n\n", " ");
                displayHeader();
                isFound = true;
                customerWithTheID = c;
                break;
            }
        }
        if (isFound) {
            System.out.println(customerWithTheID.toString(1));
            System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n", "");
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
                String name = read.nextLine();
                c.setName(name);
                System.out.print("Enter the new email address of Passenger " + name + ":\t");
                c.setEmail(read.nextLine());
                System.out.print("Enter the new Phone number of Passenger " + name + ":\t");
                c.setPhone(read.nextLine());
                System.out.print("Enter the new address of Passenger " + name + ":\t");
                c.setAddress(read.nextLine());
                System.out.print("Enter the new age of Passenger " + name + ":\t");
                c.setAge(read.nextInt());
                display(false);
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
            display(false);
        } else {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", ID);
        }
    }

    public void display(boolean showHeader) {
        Customer c1 = new Customer();
        if (showHeader) {
            displayArtWork(3);
        }
        displayHeader();
        Iterator<Customer> iterator = c1.customerCollection.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            i++;
            Customer c = iterator.next();
            System.out.println(c.toString(i));
            System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n", "");
        }
    }

    void displayHeader() {
        System.out.println();
        System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n", "");
        System.out.printf("%10s| SerialNum  |   UserID   | Passenger Names                  | Age     | EmailID\t\t       | Home Address\t\t\t     | Phone Number\t       |%n", "");
        System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+\n", "");
        System.out.println();

    }

    String randomIDDisplay(String randomID) {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i <= randomID.length(); i++) {
            if (i == 3) {
                newString.append(" ").append(randomID.charAt(i));
            } else if (i < randomID.length()) {
                newString.append(randomID.charAt(i));
            }
        }
        return newString.toString();
    }

    void displayArtWork(int option) {
        String artWork = "";
        if (option == 1) {
            artWork = """
                                        
                    d8b   db d88888b db   d8b   db       .o88b. db    db .d8888. d888888b  .d88b.  .88b  d88. d88888b d8888b.\s
                    888o  88 88'     88   I8I   88      d8P  Y8 88    88 88'  YP `~~88~~' .8P  Y8. 88'YbdP`88 88'     88  `8D\s
                    88V8o 88 88ooooo 88   I8I   88      8P      88    88 `8bo.      88    88    88 88  88  88 88ooooo 88oobY'\s
                    88 V8o88 88~~~~~ Y8   I8I   88      8b      88    88   `Y8b.    88    88    88 88  88  88 88~~~~~ 88`8b  \s
                    88  V888 88.     `8b d8'8b d8'      Y8b  d8 88b  d88 db   8D    88    `8b  d8' 88  88  88 88.     88 `88.\s
                    VP   V8P Y88888P  `8b8' `8d8'        `Y88P' ~Y8888P' `8888Y'    YP     `Y88P'  YP  YP  YP Y88888P 88   YD\s
                                                                                                                             \s
                                                                                                                             \s
                    """;
        } else if (option == 2) {
            artWork = """
                                        
                    .d8888. d88888b  .d8b.  d8888b.  .o88b. db   db       .o88b. db    db .d8888. d888888b  .d88b.  .88b  d88. d88888b d8888b.\s
                    88'  YP 88'     d8' `8b 88  `8D d8P  Y8 88   88      d8P  Y8 88    88 88'  YP `~~88~~' .8P  Y8. 88'YbdP`88 88'     88  `8D\s
                    `8bo.   88ooooo 88ooo88 88oobY' 8P      88ooo88      8P      88    88 `8bo.      88    88    88 88  88  88 88ooooo 88oobY'\s
                      `Y8b. 88~~~~~ 88~~~88 88`8b   8b      88~~~88      8b      88    88   `Y8b.    88    88    88 88  88  88 88~~~~~ 88`8b  \s
                    db   8D 88.     88   88 88 `88. Y8b  d8 88   88      Y8b  d8 88b  d88 db   8D    88    `8b  d8' 88  88  88 88.     88 `88.\s
                    `8888Y' Y88888P YP   YP 88   YD  `Y88P' YP   YP       `Y88P' ~Y8888P' `8888Y'    YP     `Y88P'  YP  YP  YP Y88888P 88   YD\s
                                                                                                                                              \s
                                                                                                                                              \s
                    """;
        } else if (option == 3) {
            artWork = """
                                        
                    .d8888. db   db  .d88b.  db   d8b   db d888888b d8b   db  d888b        .d8b.  db      db           d8888b.  .d8b.  .d8888. .d8888. d88888b d8b   db  d888b  d88888b d8888b. .d8888.\s
                    88'  YP 88   88 .8P  Y8. 88   I8I   88   `88'   888o  88 88' Y8b      d8' `8b 88      88           88  `8D d8' `8b 88'  YP 88'  YP 88'     888o  88 88' Y8b 88'     88  `8D 88'  YP\s
                    `8bo.   88ooo88 88    88 88   I8I   88    88    88V8o 88 88           88ooo88 88      88           88oodD' 88ooo88 `8bo.   `8bo.   88ooooo 88V8o 88 88      88ooooo 88oobY' `8bo.  \s
                      `Y8b. 88~~~88 88    88 Y8   I8I   88    88    88 V8o88 88  ooo      88~~~88 88      88           88~~~   88~~~88   `Y8b.   `Y8b. 88~~~~~ 88 V8o88 88  ooo 88~~~~~ 88`8b     `Y8b.\s
                    db   8D 88   88 `8b  d8' `8b d8'8b d8'   .88.   88  V888 88. ~8~      88   88 88booo. 88booo.      88      88   88 db   8D db   8D 88.     88  V888 88. ~8~ 88.     88 `88. db   8D\s
                    `8888Y' YP   YP  `Y88P'   `8b8' `8d8'  Y888888P VP   V8P  Y888P       YP   YP Y88888P Y88888P      88      YP   YP `8888Y' `8888Y' Y88888P VP   V8P  Y888P  Y88888P 88   YD `8888Y'\s
                                                                                                                                                                                                       \s
                                                                                                                                                                                                       \s
                    """;
        } else if (option == 4) {
            artWork = """
                                        
                    d8888b. d88888b  d888b  d888888b .d8888. d888888b d88888b d8888b. d88888b d8888b.      d8888b.  .d8b.  .d8888. .d8888. d88888b d8b   db  d888b  d88888b d8888b. .d8888.     \s
                    88  `8D 88'     88' Y8b   `88'   88'  YP `~~88~~' 88'     88  `8D 88'     88  `8D      88  `8D d8' `8b 88'  YP 88'  YP 88'     888o  88 88' Y8b 88'     88  `8D 88'  YP     \s
                    88oobY' 88ooooo 88         88    `8bo.      88    88ooooo 88oobY' 88ooooo 88   88      88oodD' 88ooo88 `8bo.   `8bo.   88ooooo 88V8o 88 88      88ooooo 88oobY' `8bo.       \s
                    88`8b   88~~~~~ 88  ooo    88      `Y8b.    88    88~~~~~ 88`8b   88~~~~~ 88   88      88~~~   88~~~88   `Y8b.   `Y8b. 88~~~~~ 88 V8o88 88  ooo 88~~~~~ 88`8b     `Y8b.     \s
                    88 `88. 88.     88. ~8~   .88.   db   8D    88    88.     88 `88. 88.     88  .8D      88      88   88 db   8D db   8D 88.     88  V888 88. ~8~ 88.     88 `88. db   8D     \s
                    88   YD Y88888P  Y888P  Y888888P `8888Y'    YP    Y88888P 88   YD Y88888P Y8888D'      88      YP   YP `8888Y' `8888Y' Y88888P VP   V8P  Y888P  Y88888P 88   YD `8888Y' \s
                                        
                       \s
                    d888888b d8b   db      d88888b db      d888888b  d888b  db   db d888888b                                                                                                    \s
                      `88'   888o  88      88'     88        `88'   88' Y8b 88   88 `~~88~~'                                                                                                    \s
                       88    88V8o 88      88ooo   88         88    88      88ooo88    88                                                                                                       \s
                       88    88 V8o88      88~~~   88         88    88  ooo 88~~~88    88                                                                                                       \s
                      .88.   88  V888      88      88booo.   .88.   88. ~8~ 88   88    88                                                                                                       \s
                    Y888888P VP   V8P      YP      Y88888P Y888888P  Y888P  YP   YP    YP                                                                                                       \s
                                                                                                                                                                                                \s
                                                                                                                                                                                                \s
                    """;
        } else if (option == 5) {
            artWork = """
                                        
                    d8888b. d88888b db      d88888b d888888b d88888b      d88888b db      d888888b  d888b  db   db d888888b\s
                    88  `8D 88'     88      88'     `~~88~~' 88'          88'     88        `88'   88' Y8b 88   88 `~~88~~'\s
                    88   88 88ooooo 88      88ooooo    88    88ooooo      88ooo   88         88    88      88ooo88    88   \s
                    88   88 88~~~~~ 88      88~~~~~    88    88~~~~~      88~~~   88         88    88  ooo 88~~~88    88   \s
                    88  .8D 88.     88booo. 88.        88    88.          88      88booo.   .88.   88. ~8~ 88   88    88   \s
                    Y8888D' Y88888P Y88888P Y88888P    YP    Y88888P      YP      Y88888P Y888888P  Y888P  YP   YP    YP   \s
                                                                                                                           \s
                                                                                                                           \s
                    """;
        }
        System.out.println(artWork);
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


