package sk.streetofcode.domain;
import sk.streetofcode.ability.Ability;
import sk.streetofcode.constant.Constant;

import java.util.HashMap;
import java.util.Map;

public class Hero extends GameCharacter {

    private int heroAvailablePoints;

    public Hero(String name) {
        super(name, new HashMap<>());
        this.abilities = this.getInitialAbilities();
        this.heroAvailablePoints = Constant.INITIAL_ABILITY_POINTS;
    }
    public Hero(String name, Map<Ability, Integer> abilities, int heroAvailablePoints){
         super(name, abilities);
        this.heroAvailablePoints = heroAvailablePoints;
    }

public void setAbility(Ability ability, int value){
        abilities.put(ability, value);
}

    private Map<Ability, Integer> getInitialAbilities() {
        return new HashMap<>(Map.of(
                Ability.ATTACK, 1,
                Ability.DEFENCE, 1,
                Ability.DEXTERITY, 1,
                Ability.SKILL, 1,
                Ability.LUCK, 1,
                Ability.HEALTH, 50
        ));
    }



    public void setAbilities(Map<Ability, Integer> abilities) {
        this.abilities = abilities;
    }

    public int getHeroAvailablePoints() {
        return heroAvailablePoints;
    }

    public void updateAbility(Ability ability, int delta) {
        if (ability.equals(Ability.HEALTH)) {
            this.abilities.put(ability, this.abilities.get(ability) + delta * Constant.HEALTH_OF_ONE_POINT);
        } else {
            this.abilities.put(ability, this.abilities.get(ability) + delta);
        }
    }

    public void updateAvailablePoints(int delta){
        this.heroAvailablePoints+=delta;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeroAvailablePoints(int heroAvailablePoints) {
        this.heroAvailablePoints = heroAvailablePoints;
    }
}
