package com.ramayan.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Random;

public final class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2223458616900160655L;
	
	private final String name;
	private final String description;
	private final int maxHitPoints;
	private int hitPoints;
	private int numPotions;
	private final int minDamage;
	private final int maxDamage;
	private final Random random = new Random();
	private int currentX = 0;
	private int currentY = 0;
	private Spot currentSpot;
	private transient boolean quit;

	public boolean hasQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	public Player(String name, String description, int maxHitPoints, int minDamage, int maxDamage, int numPotions) {
		this.name = name;
		this.description = description;
		this.maxHitPoints = maxHitPoints;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.numPotions = numPotions;
		this.hitPoints = maxHitPoints;
		this.currentSpot = Spot.newRegularInstance(0);
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getNumPotions() {
		return numPotions;
	}

	public void setNumPotions(int numPotions) {
		this.numPotions = numPotions;
	}

	public String getName() {
		return name;
	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	public int getMinDamage() {
		return minDamage;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public int attack() {
		return random.nextInt(maxDamage - minDamage + 1) + minDamage;
	}

	public void defend(Monster monster) {
		int attackStrength = monster.attack();
		hitPoints = (hitPoints > attackStrength) ? hitPoints - attackStrength : 0;
		System.out.printf("  " + name + " is hit for %d HP of damage (%s)\n", attackStrength, getStatus());
		if (hitPoints == 0) {
			System.out.println("  " + name + " has been defeated");
		}
	}

	public void heal() {
		if (numPotions > 0) {
			hitPoints = Math.min(maxHitPoints, hitPoints + 20);
			System.out.printf("  %s drinks healing potion (%s, %d potions left)\n", name, getStatus(), --numPotions);
		} else {
			System.out.println("  You've exhausted your potion supply!");
		}
	}

	public boolean isAlive() {
		return hitPoints > 0;
	}

	public String getStatus() {
		return "Player HP: " + hitPoints;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void movePlayer(Forest dungeon) throws IOException {
		
		boolean northPossible = dungeon.spotExist(currentX, currentY + 1);
		boolean southPossible = dungeon.spotExist(currentX, currentY - 1);
		boolean eastPossible = dungeon.spotExist(currentX + 1, currentY);
		boolean westPossible = dungeon.spotExist(currentX - 1, currentY);
		System.out.print("Where would you like to go :");
		if (northPossible) {
			System.out.print(" North (n)");
		}
		if (eastPossible) {
			System.out.print(" East (e)");
		}
		if (southPossible) {
			System.out.print(" South (s)");
		}
		if (westPossible) {
			System.out.print(" West (w)");
		}
		
		System.out.print(" Quit and Play later (q)");
		
		System.out.print(" ? ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String direction = in.readLine();
		
		if(direction.equals("q")) {
			quit = true;
			return;
		}
		
		if (direction.equals("n") && northPossible) {
			currentY++;
		} else if (direction.equals("s") && southPossible) {
			currentY--;
		} else if (direction.equals("e") && eastPossible) {
			currentX++;
		} else if (direction.equals("w") && westPossible) {
			currentX--;
		}
		
		currentSpot = dungeon.getSpot(currentX, currentY);
		currentSpot.enter(this);
	}

	public boolean isComplete() {
		return currentSpot.isRavanSpot() && currentSpot.isComplete();
	}

	public Spot getCurrentSpot() {
		return currentSpot;
	}

	public void setCurrentSpot(Spot currentSpot) {
		this.currentSpot = currentSpot;
	}
	
}