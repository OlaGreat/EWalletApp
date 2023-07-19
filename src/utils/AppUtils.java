package utils;

import java.math.BigInteger;
import java.util.Random;

public class AppUtils {
    private static int countId;

    public static int generateId(){
        countId+=1;
        return countId;
    }
    public static boolean accountNumberChecker(String accountNumber){
        return accountNumber.length() == 10;
    }
    public static final int NINE = BigInteger.valueOf(9).intValue();

    public static String generateStringId(){
        StringBuilder id = new StringBuilder();
        String character = "abcdefghijklmnopqrstuvwsyz0123456789";
        Random random = new Random();
        for (int i = 0; i < NINE; i++) {
            int generateId = random.nextInt(character.length());
            char randomChar = character.charAt(generateId);
            id.append(randomChar);
        }
        return id.toString();
    }

}
