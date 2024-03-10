import java.util.Scanner;

public class Main{
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome to the Gladiator game!");
        System.out.println("Enter your name:");
        final  String name = scanner.nextLine();
        final Hero hero = new Hero(name);
        System.out.println("Hello " + hero.getName() + ". Let's start the game!" );
    }
}
