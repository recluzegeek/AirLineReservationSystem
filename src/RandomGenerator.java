import java.util.Random;

public class RandomGenerator {
    private String randomNum;

    RandomGenerator() {
        this.randomNum = null;
    }

    public void randomIDGen() {
        Random rand = new Random();
        String randomID = Integer.toString(rand.nextInt(1000000));
        while (Integer.parseInt(randomID) < 20000) {
            randomID = Integer.toString(rand.nextInt(1000000));
        }
        setRandomNum(randomID);
    }

    public void setRandomNum(String randomNum) {
        this.randomNum = randomNum;
    }

    public String getRandomNumber() {
        return randomNum;
    }

    public int randomNumOfSeats() {
        Random random = new Random();
        int numOfSeats = random.nextInt(500);
        return numOfSeats;
    }

    public String randomFlightNumbGen() {
        Random random = new Random();
        String randomAlphabets = "";
        for (int i = 0; i < 3; i++) {
            randomAlphabets += String.valueOf((char) (random.nextInt(26) + 'a'));
        }
        randomAlphabets += "-" + randomNumOfSeats();
        return randomAlphabets;
    }

}
