package utils;

import java.util.Scanner;

public class Console {
    public static String getString(String message){
        System.out.println(message);
        return new Scanner(System.in).nextLine().trim();
    }

    public static float getFloat(String message){
        System.out.println(message);
        return new Scanner(System.in).nextFloat();
    }
}
