package com.ramayan.model;
import java.io.Serializable;
import java.util.Random;

import com.ramayan.constant.RamayanCharacter;

public final class Monster implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2223475851876970762L;
	
	private final String name;
    private final String description;
    private int hitPoints;
    private final int minDamage;
    private final int maxDamage;
    private final static Random random = new Random();

    public static Monster newRandomInstance(int i) {
    	
        if (i == 0) {
            return new Monster("Shurpanakha", RamayanCharacter.SHURPANAKHA.toString(), 40, 8, 12);
        } else if (i == 1) {
            return new Monster("Kumbhakarna", RamayanCharacter.KUMBHAKARANA.toString(), 26, 4, 6);
        } else if (i == 2) {
            return new Monster("Kubera", RamayanCharacter.KUBERA.toString(), 26, 4, 6);
        } else if (i == 3) {
            return new Monster("Simhika", RamayanCharacter.SIMHIKA.toString(), 18, 1, 2);
        } else if (i == 4) {
            return new Monster("Dushana", RamayanCharacter.DUSHANA.toString(), 18, 1, 2);
        } else if (i == 5) {
            return new Monster("Atikaya", RamayanCharacter.ATIKAYA.toString(), 18, 1, 2);
        }  else if (i == 6) {
            return new Monster("Indrajit", RamayanCharacter.INDRAJIT.toString(), 18, 1, 2);
        } else {
            return new Monster("Ahiravan", RamayanCharacter.AHIRAVAN.toString(), 18, 1, 2);
        } 
    }

    public static Monster newRavanInstance() {
        return new Monster("Ravan", RamayanCharacter.RAVAN.toString(), 60, 10, 20);
    }

    private Monster(String name, String description, int hitPoints, int minDamage, int maxDamage) {
        this.name = name;
        this.description = description;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.hitPoints = hitPoints;
    }

    public String getName() {
		return name;
	}

	@Override
    public String toString() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return "Monster HP: " + hitPoints;
    }

    public int attack() {
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public void defend(Player player) {
        int attackStrength = player.attack();
        hitPoints = (hitPoints > attackStrength) ? hitPoints - attackStrength : 0;
        System.out.printf("  %s hits %s for %d HP of damage (%s)\n", player, name, attackStrength,
                getStatus());
        if (hitPoints == 0) {
            System.out.println("  " + player.getName() + " killed " + name);
        }
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

}