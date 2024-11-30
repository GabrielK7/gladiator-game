package sk.streetofcode;

import sk.streetofcode.service.GameManager;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       final GameManager gameManager = new GameManager();
       try {
           gameManager.startGame();
       }catch (IOException e){
           System.out.println("Error");
       }


      /*  System.out.println("You have " + actualPoints + " points to spend.");
        System.out.println("Choose ability to upgrade:");
        System.out.println("0. Explain abilities\n1. Attack\n2. Defence\n3. Dexterity\n4. Skill\n5. Luck\n6. Health");
        //updateNumber = scanner.nextInt();
        while (abilityUpdate.getPointsToSpend() > 0) {
            updateNumber = scanner.nextInt();
            abilityUpdate.setUpdateNum(updateNumber);
            abilityUpdate.updateAbility();

        }
        System.out.println("Continue.....");

    }*/


    }


}