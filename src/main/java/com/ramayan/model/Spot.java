package com.ramayan.model;

import java.io.IOException;
import java.io.Serializable;

public final class Spot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2361635112573884034L;

	private final String description;
	private final Monster monster;
	private final Boolean isRavanSpot;

	private Spot(String description, Monster monster, Boolean isRavanSpot) {
		this.description = description;
		this.monster = monster;
		this.isRavanSpot = isRavanSpot;
	}

	public static Spot newRegularInstance(int i) {

		String spotDescription = null;
		if (i == 0) {
			spotDescription = "Shurpanakha is a oversized breasts, She is a sister of Ravan.";
		} else if (i == 1) {
			spotDescription = "Kumbhakarna is Ravan brother, A Big Giant monster ";
		} else if (i == 2) {
			spotDescription = "Kubera is step brother of Ravan, He is a clever fighter";
		} else if (i == 3) {
			spotDescription = "Simhika is a favour safe guard of Ravan, she follow all orders of Ravan, This time its instructed to kill the one who is coming towards him";
		} else if (i == 4) {
			spotDescription = "Dushana is Ravan's step daughter, She is a evil who killed many innocents";
		} else if (i == 5) {
			spotDescription = "Atikaya is most strongest daughter of ravan and clever as well";
		} else if (i == 6) {
			spotDescription = "Indrajit is Ravan's real son. Kill him to get one step closer to Ravan";
		} else {
			spotDescription = "Ahiravan is step son of Ravan but stronger than other sons";
		}
		return new Spot(spotDescription, Monster.newRandomInstance(i), false);
	}

	public static Spot newRavanInstance() {
		return new Spot(
				"You have killed all mosters in way to reach Ravan, Ravan is very strong as compare all monsters. ",
				Monster.newRavanInstance(), true);
	}

	public boolean isRavanSpot() {
		return isRavanSpot;
	}

	public boolean isComplete() {
		return !monster.isAlive();
	}

	@Override
	public String toString() {
		return description;
	}

	public void enter(Player player) throws IOException {
		
		if (monster.isAlive()) {
			System.out.println("You encounter " + monster + "\n");
			System.out.println(description);
			new Battle(player, monster);
		} else {
			System.out.println("You have already Killed " + monster.getName()
					+ " Move forward to differnet Direction to reach closer to Ravan");
		}
	}

}