package tools;

import java.util.Random;

public class NumberOperation {

    /**
     *
     * @param value the integer number
     * @return the random number
     */
    public static int getRandomNumberIncludeValue(int value) {
        return getRandomNumber(value + 1);
    }

    public static int getRandomNumber(int value) {
        return (int) (Math.random() * (value));
    }

    public static int getRandomOddNumber(int value) {
       return  (new Random().nextInt(value / 2)) * 2 +1;
    }

    public static int getRandomEvenNumber(int value) {
        return getRandomOddNumber(value) - 1 ;
    }

    public static boolean isRandomGreaterThanValue(double checkValue) {
        double randomNumber = Math.random();
        return randomNumber > checkValue;
    }
}
