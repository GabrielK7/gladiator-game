package sk.streetofcode.service;

import sk.streetofcode.ability.Ability;
import sk.streetofcode.constant.Constant;
import sk.streetofcode.domain.Enemy;
import sk.streetofcode.domain.GameCharacter;
import sk.streetofcode.domain.Hero;
import sk.streetofcode.utility.InputUtils;
import sk.streetofcode.utility.PrintUtils;

import java.util.Map;
import java.util.Random;

public class BattleService {

    private final Random random;

    public BattleService() {
        this.random = new Random();
    }

    public boolean battle(Hero hero, Enemy enemy) {
        final Map<Ability, Integer> heroAbilities = hero.getAbilities();
        final Map<Ability, Integer> enemyAbilities = enemy.getAbilities();
        System.out.println("you start the battle first");
        PrintUtils.printDivider();
        boolean isHeroTurn = true;
        while (true) {
            final int heroLife = heroAbilities.get(Ability.HEALTH);
            final int enemyLife = enemyAbilities.get(Ability.HEALTH);
            System.out.println("Your life:" + heroLife);
            System.out.println("Enemy life:" + enemyLife);

            if (isHeroTurn) {
                this.battleRound(hero, enemy);
                isHeroTurn = false;
            } else {
                this.battleRound(enemy, hero);
                isHeroTurn = true;
            }

            if (heroLife <= 0) {
                return false;
            } else if (enemyLife <= 0) {
                return true;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public boolean isHeroReadyToBattle(Hero hero, Enemy enemy) {
        System.out.println(hero.getName() + " VS " + enemy.getName());
        System.out.println("View your abilities:");
        PrintUtils.printAbilities(hero);
        System.out.println("View enemy abilities:");
        PrintUtils.printAbilities(enemy);
        System.out.println("Are you ready to fight?");
        System.out.println("0. No");
        System.out.println("1. Yes");

        final int choice = InputUtils.readInt();
        switch (choice) {
            case 0 -> {
                System.out.println("You have escaped from battle");
                return false;
            }
            case 1 -> {
                System.out.println("Let the battle begin");
                return true;
            }
            default -> {
                System.out.println("Invalid choice");
                return false;
            }
        }

    }

    private void battleRound(GameCharacter attacker, GameCharacter defender) {
        final Map<Ability, Integer> attackerAbilities = attacker.getAbilities();
        final Map<Ability, Integer> defenderAbilities = defender.getAbilities();
        //calculate attack power of attacker
        final int minAttack = attackerAbilities.get(Ability.ATTACK);
        final int maxAttack = minAttack + attackerAbilities.get(Ability.DEXTERITY) + attackerAbilities.get(Ability.SKILL);
        final int attackPower = random.nextInt(maxAttack - minAttack + 1) + maxAttack;
        // calculate defence power of defender
        final int minDefence = defenderAbilities.get(Ability.DEFENCE);
        final int maxDefence = minDefence + defenderAbilities.get(Ability.DEXTERITY);
        final int defencePower = random.nextInt(maxDefence - minDefence + 1) + minDefence;

        // calculate damage
        final boolean isCriticalHit = (random.nextInt(101) + 1) < attackerAbilities.get(Ability.LUCK);
        int damage = Math.max(0, attackPower - defencePower);
        if (isCriticalHit) {
            System.out.println("Critical hit!");
            damage *= Constant.CRITICAL_HIT_MULTIPLIER;
        }

        System.out.println(attacker.getName() + " attacks " + defender.getName() + " with " + damage + " damage!");
        defender.recieveDamage(damage);
        System.out.println(defender.getName() + " has " + defenderAbilities.get(Ability.HEALTH) + " health");
        PrintUtils.printDivider();
    }
}
