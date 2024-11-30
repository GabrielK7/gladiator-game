package sk.streetofcode.domain;

import sk.streetofcode.ability.Ability;

import java.util.Map;

public class Enemy extends GameCharacter{
    public Enemy(String name, Map<Ability, Integer> abilities) {
        super(name, abilities);
    }
}
