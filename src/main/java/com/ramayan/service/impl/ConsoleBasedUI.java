package com.ramayan.service.impl;

import java.util.List;
import java.util.Scanner;

import com.ramayan.model.Player;
import com.ramayan.service.UIservice;

public class ConsoleBasedUI implements UIservice {

	Scanner scan = new Scanner(System.in);

	@Override
	public void printStory() {
		System.out.println("You are in a Forest.\n" + "Your princess is kidnapped by Ravan \n"
				+ "This forest is surrounded by monsters at different spots\n"
				+ "Navigate to different spots with N,E,W,S keys and Q to Quit anytime \n"
				+ "You need to fight with monster at each spot to reach Ravan ");
		System.out.println("\n--------------------------------------\n");

		System.out.flush();
	}

	@Override
	public void printInstructions() {
		System.out.println("\nWhich direction do you want to move?\n" + "NORTH(N/n) , EAST(E/e),WEST(W/w),SOUTH(S/s)\n"
				+ "Type Quit/Q/q to exit");

	}

	@Override
	public void displayFinishedGameMessage() {
		System.out.println("Congratulations !! you have saved your princess. Now you can go back to your kingdom. ");
	}

	@Override
	public void displayUnFinishedGameMessage() {
		System.out.println("Your game will be saved.You can resume the game by selecting same user");
	}

	@Override
	public void displayExploreError() {
		System.err.println("Invalid input.Only N/E/W/S/Q are allowed");
	}

	@Override
	public String readUserInputString() {
		String input = scan.nextLine();
		return input;
	}

	@Override
	public int readUserInputInt() {
		int input = scan.nextInt();
		scan.nextLine();// takes care of \n character after number
		return input;
	}

	@Override
	public void destroy() {
		scan.close();
	}

	@Override
	public void displayUsers(List<Player> players) {
		printHeader();
		int i = 0;
		for (Player user : players) {
			System.out
					.println(++i + ")   " + user.getName() + "\t" + user.getHitPoints() + "\t" + user.getNumPotions());
		}
		System.out.println(++i + ")   create New player");
		System.out.println(++i + ")   Delete A player");
		System.out.flush();
	}

	private void printHeader() {
		System.out.println("Select a player from the following");
		System.out.println("----------------------------------");
		System.out.println("\tName\tHit Points\tNumber of Potions Left");
		System.out.println("----------------------------------");
		System.out.flush();
	}

	@Override
	public void displayInvalidOptionMessage() {
		System.out.println("Invalid option.Try again");
	}

	@Override
	public void confirmUserDeletion() {
		System.out.println("To confirm deletion - enter the name of user.To cancel - enter #cancel");
	}

	@Override
	public void userIntroMessage() {
		System.out.println("Let's get introduced");
		System.out.println("What should I call you?");
	}

	@Override
	public void exitMessage() {
		System.out.println("Goodbye!");
	}

	@Override
	public void displayWinMessage() {
		System.out.println("You have saved your Princess from Ravan.You won!!");
	}

	@Override
	public void displayLooseMessage() {
		System.out.println("You died.You lost the game. Play again to save the princess");
	}

	@Override
	public void printUserDescription(Player selectedUser) {
		System.out.println(selectedUser.getName() + selectedUser.getDescription());
	}

}
