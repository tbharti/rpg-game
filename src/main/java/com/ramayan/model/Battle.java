package com.ramayan.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ramayan.utils.ASCIIArt;

public final class Battle {

	public Battle(Player player, Monster monster) throws IOException {
		new ASCIIArt().drawString(monster.getDescription(), "*");
		System.out.println(
				"Battle with " + monster + " starts (" + player.getStatus() + " / " + monster.getStatus() + ")");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (player.isAlive() && monster.isAlive()) {
			System.out.print("Attack (a) or heal (h)? ");
			String action = in.readLine();
			if (action.equals("h")) {
				player.heal();
			} else {
				monster.defend(player);
			}
			if (monster.isAlive()) {
				player.defend(monster);
			}
		}
	}

}