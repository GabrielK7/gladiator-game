package sk.streetofcode.ability;

import sk.streetofcode.ability.Ability;
import sk.streetofcode.domain.Hero;

import java.util.Map;

public class AbilityUpdate {

    private int pointsToSpend = 7;
    private int updateNum;
    Hero hero = new Hero("Test");
    Map<Ability, Integer> map = hero.getAbilities();

    public void updateAbility() {
        switch (updateNum) {

            case 0 -> {
                for (Ability ability : Ability.values()) {
                    System.out.println(ability + ": " + ability.getDescription());
                }
                System.out.println("You have " + pointsToSpend + " points to spend.");
                System.out.println("Choose ability to upgrade:");
                System.out.println("0. Explain abilities\n1. Attack\n2. Defence\n3. Dexterity\n4. Skill\n5. Luck\n6. Health");

            }
            case 1 -> {

                map.put(Ability.ATTACK, map.get(Ability.ATTACK) + 1);
                pointsToSpend--;
                printAbilities();
            }
            case 2 -> {
                map.put(Ability.DEFENCE, map.get(Ability.DEFENCE) + 1);
                pointsToSpend--;
                printAbilities();
            }
            case 3 -> {
                map.put(Ability.DEXTERITY, map.get(Ability.DEXTERITY) + 1);
                pointsToSpend--;
                printAbilities();
            }
            case 4 -> {
                map.put(Ability.SKILL, map.get(Ability.SKILL) + 1);
                pointsToSpend--;
                printAbilities();
            }
            case 5 -> {
                map.put(Ability.LUCK, map.get(Ability.LUCK) + 1);
                pointsToSpend--;
                printAbilities();
            }
            case 6 -> {
                map.put(Ability.HEALTH, map.get(Ability.HEALTH) + 1);
                pointsToSpend--;
                printAbilities();
            }

        }
    }

    private void printAbilities() {
        System.out.println("Num to update: " + updateNum);
        System.out.println("You have upgraded " + Ability.values()[updateNum - 1]);

        for (Map.Entry<Ability, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + ", ");
        }
        System.out.println();
        System.out.println("You have " + getPointsToSpend() + " points to spend.");
        System.out.println("Choose ability to upgrade:");
        System.out.println("0. Explain abilities\n1. Attack\n2. Defence\n3. Dexterity\n4. Skill\n5. Luck\n6. Health");

    }

    public int getPointsToSpend() {
        return pointsToSpend;
    }

//    public int getUpdateNum() {
//        return updateNum;
//    }


    public Map<Ability, Integer> getMap() {
        return map;
    }

    public void setUpdateNum(int updateNum) {
        this.updateNum = updateNum;
    }
}
