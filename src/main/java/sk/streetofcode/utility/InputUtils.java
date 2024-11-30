package sk.streetofcode.utility;

import java.util.Scanner;

public class InputUtils {

    public static Scanner scanner = new Scanner(System.in);

    public static  String readString(){
        return scanner.nextLine();
    }
    public static int readInt(){
        while(true){
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            }catch (Exception e){
                System.out.println("Invalid input, try again.");
                scanner.nextLine();
            }

        }
    }
}
