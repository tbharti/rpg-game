package com.ramayan.service;


import java.util.List;

import com.ramayan.model.Player;

public interface UIservice {
	void printStory();
	void printInstructions();
	void displayFinishedGameMessage();
	void displayUnFinishedGameMessage();
	void displayExploreError();
	String readUserInputString();
	int readUserInputInt();
	void destroy();
	void displayUsers(List<Player> users);
	void displayInvalidOptionMessage();
	void confirmUserDeletion();
	void userIntroMessage();
	void exitMessage();
	void displayWinMessage();
	void displayLooseMessage();
	void printUserDescription(Player selectedUser);
}
