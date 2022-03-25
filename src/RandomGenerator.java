import java.util.Random;

public class RandomGenerator {
    private String randomNum;
    private final String[] destinations = {"Karachi", "Bangkok", "Jakarta", "Bangkok", "Islamabad", "New York City",
            "Lahore", "Gilgit Baltistan", "Jeddah", "Riadh", "Delhi", "Hong Kong", "Beijing", "Tokyo",
            "Kuala Lumpur", "Sydney", "Melbourne", "Cape Town", "Madrid", "Dublin", "Johannesburg", "London", "Los Angeles", "Brisbane", "Amsterdam",
            "Stockholm", "Frankfurt", "Istanbul", "Bhutan", "Dhaka", "Munich", "Perth", "Mexico", "California", "Kabul", "Yangon", "Lagos", "Santiago",
            "New Taipei City", "Rio de Janeiro", "Seoul", "Yokohama", "Ankara", "Casablanca", "Shenzhen", "Baghdad", "Alexandria", "Pune", "Shanghai",
            "Nairobi", "Tehran", "Saint Petersburg", "Hanoi", "Sialkot", "Berlin"
    };

    /* Zero-Argument Constructor */
    RandomGenerator() {
        this.randomNum = null;
    }

    /* Generates Random ID for the Customers....*/
    public void randomIDGen() {
        Random rand = new Random();
        String randomID = Integer.toString(rand.nextInt(1000000));
        while (Integer.parseInt(randomID) < 20000) {
            randomID = Integer.toString(rand.nextInt(1000000));
        }
        setRandomNum(randomID);
    }
    /*This method sets the destinations for each of the flights from the above destinations randomly.....*/
    public String[] randomDestinations() {
        Random rand = new Random();
        int randomCity1 = rand.nextInt(destinations.length);
        int randomCity2 = rand.nextInt(destinations.length);
        String fromWhichCity = destinations[randomCity1];
        while (randomCity2 == randomCity1) {
            randomCity2 = rand.nextInt(destinations.length);
        }
        String toWhichCity = destinations[randomCity2];
        String[] chosenDestinations = new String[2];
        chosenDestinations[0] = fromWhichCity;
        chosenDestinations[1] = toWhichCity;
        return chosenDestinations;
    }


    public void setRandomNum(String randomNum) {
        this.randomNum = randomNum;
    }

    public String getRandomNumber() {
        return randomNum;
    }

    /*Generates the Random Number of Seats for each flight*/
    public int randomNumOfSeats() {
        Random random = new Random();
        int numOfSeats = random.nextInt(500);
        while (numOfSeats < 75) {
            numOfSeats = random.nextInt(500);
        }
        return numOfSeats;
    }

    /*Generates the Unique Flight Number....*/
    public String randomFlightNumbGen(int uptoHowManyLettersRequired, int divisible) {
        Random random = new Random();
        StringBuilder randomAlphabets = new StringBuilder();
        for (int i = 0; i < uptoHowManyLettersRequired; i++) {
            randomAlphabets.append((char) (random.nextInt(26) + 'a'));
        }
        randomAlphabets.append("-").append(randomNumOfSeats() / divisible);
        return randomAlphabets.toString();
    }

}
