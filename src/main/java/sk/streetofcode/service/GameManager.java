package sk.streetofcode.service;

import sk.streetofcode.ability.Ability;
import sk.streetofcode.ability.HeroAbilityManager;
import sk.streetofcode.constant.Constant;
import sk.streetofcode.domain.Enemy;
import sk.streetofcode.domain.Hero;
import sk.streetofcode.domain.LoadedGame;
import sk.streetofcode.utility.EnemyGenerator;
import sk.streetofcode.utility.InputUtils;
import sk.streetofcode.utility.PrintUtils;

import java.io.IOException;
import java.util.Map;


public class GameManager {
    private Hero hero;
    private final HeroAbilityManager heroAbilityManager;
    private int currentLevel;
    private final FileService fileService;
    private final Map<Integer, Enemy> enemiesByLevel;
    private final BattleService battleService;

    public GameManager() {
        this.hero = new Hero("");
        this.heroAbilityManager = new HeroAbilityManager(hero);
        this.currentLevel = Constant.INITIAL_LEVEL;
        this.fileService = new FileService();
        this.enemiesByLevel = EnemyGenerator.createEnemies();
        this.battleService = new BattleService();
    }

    public void startGame() throws IOException {
        this.initGame();
        while (this.currentLevel <= this.enemiesByLevel.size()) {
            final Enemy enemy = this.enemiesByLevel.get(currentLevel);
            System.out.println("0. Fight " + enemy.getName() + "(Level " + this.currentLevel + ")");
            System.out.println("1. Upgade abilities (" + hero.getHeroAvailablePoints() + " points to spend.)");
            System.out.println("2. Save game");
            System.out.println("3. Exit game");

            final int choice = InputUtils.readInt();

            switch (choice) {
                case 0 -> {
                    //is hero ready to battle?
                    if (this.battleService.isHeroReadyToBattle(this.hero, enemy)) {
                        //TODO battle
                        final int heroHealthBeforeBattle = this.hero.getAbilities().get(Ability.HEALTH);

                        //battle TODO
                        final boolean hasHeroWon = this.battleService.battle(this.hero, enemy);
                        if (hasHeroWon) {
                            PrintUtils.printDivider();
                            System.out.println("You have won this battle! You have gained " + this.currentLevel + " ability points.");
                            this.hero.updateAvailablePoints(this.currentLevel);
                            this.currentLevel++;
                        } else {
                            System.out.println("You have lost");
                        }
                        //restore health
                        this.hero.setAbility(Ability.HEALTH, heroHealthBeforeBattle);
                        System.out.println("Zou have full health now");
                        PrintUtils.printDivider();

                        this.currentLevel++;
                    }

                }
                case 1 -> {
                    //TODO upgrade abilities
                    /*System.out.println("Your abilities are:");
                    PrintUtils.printAbilities(hero);
                    System.out.println("0. Go back");
                    System.out.println("1. Spend points (" + hero.getHeroAvailablePoints() + " points left)");
                    System.out.println("2. Remove points");
                    final int upgradeChoice = InputUtils.readInt();*/
                    //upgradeAbilities(upgradeChoice);
                    this.upgradeAbilities();

                }
                case 2 -> {
                    //TODO Save game
                    fileService.saveGame(this.hero, this.currentLevel);


                }
                case 3 -> {
                    //TODO exit games
                    System.out.println("Are you sure?");
                    System.out.println("0. No");
                    System.out.println("1. Yes");
                    final int exitChoice = InputUtils.readInt();

                    if (exitChoice == 1) {
                        System.out.println("Bye");
                        return;
                    }
                    System.out.println("Continuing...");
                    PrintUtils.printDivider();
                }
                default -> System.out.println("Invalid choice");

            }
        }
        System.out.println("You have won the game! Congratulations!");
    }


    private void initGame() throws IOException {
        System.out.println("Welcome to the Gladiator game!");
        System.out.println("0. Start game");
        System.out.println("1. Load game");
        final int startChoice = InputUtils.readInt();
        switch (startChoice) {
            case 0 -> System.out.println("Let's start the game");
            case 1 -> {
                final LoadedGame loadGame = fileService.loadGame();
                if (loadGame != null) {
                    this.hero = loadGame.getHero();
                    this.currentLevel = loadGame.getLevel();
                    return;
                }
            }
            default -> System.out.println("Invalid choice");

        }

        System.out.println("Enter your name:");
        final String name = InputUtils.readString();
        this.hero.setName(name);
        System.out.println("Hello " + hero.getName() + "! Let's start the game!");
        PrintUtils.printDivider();
        System.out.println("Your abilities are:");
        PrintUtils.printAbilities(hero);
        PrintUtils.printDivider();
        System.out.println();
        this.heroAbilityManager.spendHeroAvailablePoints();


    }

   /* private void upgradeAbilities(int upgradeChoice) {
        while (true) {
            switch (upgradeChoice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.println("Your abilities are: ");
                    PrintUtils.printAbilities(hero);
                    if (hero.getHeroAvailablePoints() == 0) {
                        System.out.println("No available points to spend!");
                    } else {
                        heroAbilityManager.spendHeroAvailablePoints();
                    }
                    return;

                }
                case 2 -> {
//Remove points
                    System.out.println("0. All done");
                    System.out.println("1. Attack");
                    System.out.println("2. Defence");
                    System.out.println("3. Dexterity");
                    System.out.println("4. Skill");
                    System.out.println("5. Luck");
                    System.out.println("6. Health");

                    final int removeChoice = InputUtils.readInt();
                    Ability ability;
                    int actualPoints = hero.getHeroAvailablePoints() + 1;
                    switch (removeChoice) {
                        case 0 -> {
                            return;
                        }
                        case 1 -> {
                            ability = Ability.ATTACK;
                            hero.updateAbility(ability, -1);
                            PrintUtils.printAbilities(hero);
                            hero.setHeroAvailablePoints(actualPoints);

                        }
                        case 2 -> {
                            ability = Ability.DEFENCE;
                            hero.updateAbility(ability, -1);
                            PrintUtils.printAbilities(hero);
                            hero.setHeroAvailablePoints(actualPoints);
                        }
                        case 3 -> {
                            ability = Ability.DEXTERITY;
                            hero.updateAbility(ability, -1);
                            PrintUtils.printAbilities(hero);
                            hero.setHeroAvailablePoints(actualPoints);
                        }
                        case 4 -> {
                            ability = Ability.SKILL;
                            hero.updateAbility(ability, -1);
                            PrintUtils.printAbilities(hero);
                            hero.setHeroAvailablePoints(actualPoints);
                        }
                        case 5 -> {
                            ability = Ability.LUCK;
                            hero.updateAbility(ability, -1);
                            PrintUtils.printAbilities(hero);
                            hero.setHeroAvailablePoints(actualPoints);
                        }
                        case 6 -> {
                            ability = Ability.HEALTH;
                            hero.updateAbility(ability, -1);
                            PrintUtils.printAbilities(hero);
                            hero.setHeroAvailablePoints(actualPoints);
                        }
                        default -> {
                            System.out.println("Invalid index");
                            continue;
                        }
                    }
                }
            }
        }
    }*/

    public int getCurrentLevel() {
        return currentLevel;
    }

    private void upgradeAbilities() {
        System.out.println("Your abilitie are:");
        PrintUtils.printAbilities(this.hero);


        System.out.println("0. Go back");
        System.out.println("1. Spend points (" + hero.getHeroAvailablePoints() + " points to spend)");
        System.out.println("2. Remove points");

        final int choice = InputUtils.readInt();
        switch (choice) {
            case 0 -> {
            }
            case 1 -> this.heroAbilityManager.spendHeroAvailablePoints();
            case 2 -> this.heroAbilityManager.removeHeroAvailablePoints();
            case 3 -> System.out.println("Invalid choice!");
        }
    }


}
