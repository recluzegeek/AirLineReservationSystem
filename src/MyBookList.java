import java.util.Scanner;

public class MyBookList {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.print("Enter the Size for the number of books(rows) :     ");
        int numOfRows = read.nextInt();
        int currentSize = 0;
        while (numOfRows < 5) {
            System.out.print("ERROR!! Number of Rows should be greater than or equal to 5. Pls enter again :    ");
            numOfRows = read.nextInt();
        }
        String[][] books = new String[numOfRows][3];
        boolean exit = true;
        while (exit) {
            System.out.println("\n1.  Press 0 to Exit");
            System.out.println("2.  Press 1 to Add a new Book");
            System.out.println("3.  Press 2 to Search by Title");
            System.out.println("4.  Press 3 to Search by Price");
            System.out.println("5.  Press 4 to Sort by Title");
            System.out.println("6.  Press 5 to Sort by Pages");
            System.out.println("7.  Press 6 to Display the Most Expensive Book");
            System.out.println("8.  Press 7 to Display all Books");
            int userChoice = read.nextInt();
            while (userChoice < 0 || userChoice > 8) {
                System.out.print("ERROR!! Number should be between 0 - 7. Pls enter the number again :  ");
                userChoice = read.nextInt();
            }
            if (userChoice == 0) {
                exit = false;
            } else if (userChoice == 1) {
                boolean numOfBooks = add(books, currentSize);
                if (numOfBooks) {
                    currentSize++;
                } else {
                    System.out.println("ERROR!! The list is full. No more books can be added. Here is a full list... ");
                    displayAllBooks(books);
                }
            } else if (userChoice == 2) {
                Scanner inputForTitle = new Scanner(System.in);
                System.out.print("Enter the Title of the Book you want to Search for :      ");
                String titleInput = inputForTitle.nextLine();
                if (searchByTitle(books, titleInput, currentSize) == -1) {
                    System.out.println("Book doesn't found/exists.....");
                }
            } else if (userChoice == 3) {
                Scanner inputForPrice = new Scanner(System.in);
                System.out.print("Enter the Price of the Book you want to Search for :      ");
                String priceInput = inputForPrice.nextLine();
                if (searchByPrice(books, priceInput, currentSize) == -1) {
                    System.out.println("Couldn't found any book with the matching price.....");
                }
            } else if (userChoice == 4) {
                System.out.println("Sorting the Given Array by Title...");
                sortByTitle(books, currentSize);
            } else if (userChoice == 5) {
                System.out.println("Sorting the Given Array by PageNums...");
                sortByPage(books, currentSize);
            } else if (userChoice == 6) {
                showMostExpensive(books, currentSize);
            } else if (userChoice == 7) {
                displayAllBooks(books);
            }
        }
    }

    static boolean add(String[][] arr, int currentSize) {
        Scanner read = new Scanner(System.in);
        System.out.println();
        if (currentSize < arr.length) {
            System.out.print("Enter the name for book " + (currentSize + 1) + " :     ");
            arr[currentSize][0] = read.nextLine();
            System.out.print("Enter the price of " + arr[currentSize][0] + "   :     ");
            arr[currentSize][1] = read.nextLine();
            System.out.print("Enter the number of pages of " + arr[currentSize][0] + "   :     ");
            arr[currentSize][2] = read.nextLine();
            return true;
        } else {
            return false;
        }
    }

    static int searchByTitle(String[][] books, String name, int currentSize) {
        String[][] showArray = new String[1][3];
        int index = -1;
        for (int i = 0; i < currentSize; i++) {
            if (books[i][0].equalsIgnoreCase(name)) {
                System.out.println("Book Found and is at Index Num :  " + i);
                index = i;
                for (int j = 0; j < 3; j++) {
                    showArray[0][j] = books[i][j];
                }
                displayAllBooks(showArray);
            }
        }
        return index;
    }

    static int searchByPrice(String[][] books, String price, int currentSize) {
        String[][] showArray = new String[1][3];
        for (int i = 0; i < currentSize; i++) {
            if (books[i][1].equals(price)) {
                System.out.println("Book Found and is at Index Num :  " + i);
                for (int j = 0; j < 3; j++) {
                    showArray[0][j] = books[i][j];
                }
                displayAllBooks(showArray);
                return i;
            }
        }
        return -1;
    }

    static void sortByTitle(String[][] books, int currentSize) {
        String[][] copiedArray = copyArray(books);
        int col = 0, result;
        String low, priceTemp, pageTemp;
        for (int row = 0; row < currentSize - 1; row++) {
            for (int j = row + 1; j < currentSize; j++) {
                low = copiedArray[row][col];
                priceTemp = copiedArray[row][col + 1];
                pageTemp = copiedArray[row][col + 2];
                result = low.compareToIgnoreCase(copiedArray[j][col]);
                if (result > 0) {
                    copiedArray[row][col] = copiedArray[j][col];
                    copiedArray[j][col] = low;
                    copiedArray[row][col + 1] = copiedArray[j][col + 1];
                    copiedArray[j][col + 1] = priceTemp;
                    copiedArray[row][col + 2] = copiedArray[j][col + 2];
                    copiedArray[j][col + 2] = pageTemp;
                }
            }
        }
        MyBookList.displayAllBooks(copiedArray);
    }

    static void sortByPage(String[][] books, int currentSize) {
        String[][] copiedArray;
        copiedArray = copyArray(books);
        int col = 2, pageTemp;
        String[] arr;
        for (int row = 0; row < currentSize; row++) {
            for (int j = 0; j < currentSize; j++) {
                pageTemp = Integer.parseInt(copiedArray[row][col]);
                if (pageTemp < Integer.parseInt(copiedArray[j][col])) {
                    arr = copiedArray[row];
                    copiedArray[row] = copiedArray[j];
                    copiedArray[j] = arr;
                }
            }
        }
        displayAllBooks(copiedArray);
    }

    static void showMostExpensive(String[][] books, int currentSize) {
        double mostExpensiveBook = Double.parseDouble(books[0][1]);
        int index = 0;
        String[][] expensiveArray = new String[1][3];
        for (int i = 0; i < currentSize; i++) {
            if (mostExpensiveBook < Double.parseDouble(books[i][1])) {
                mostExpensiveBook = Double.parseDouble(books[i][1]);
                index = i;
            }
        }
        for (int j = 0; j < 3; j++) {
            expensiveArray[0][j] = books[index][j];
        }
        displayAllBooks(expensiveArray);
    }

    static void displayAllBooks(String[][] books) {
        System.out.printf("+---------------------------------+-------------+-------------+%n");
        System.out.printf("| Book Name                       |   Price     | Num of Pages|%n");
        System.out.print("+---------------------------------+-------------+-------------+");
        for (int i = 0; i < books.length; i++) {
            int count = 0;
            System.out.println();
            for (int j = 0; j < 3; j++) {
                if (count == 0) {
                    System.out.printf(" %-32s |  ", books[i][j]);
                    count++;
                } else {
                    System.out.printf(" %-9s |  ", books[i][j]);
                }
            }
        }
        System.out.print("\n+---------------------------------+-------------+-------------+\n");
    }

    static String[][] copyArray(String[][] array) {
        String[][] copiedArray = new String[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                copiedArray[i][j] = array[i][j];
            }
        }
        return copiedArray;
    }
}