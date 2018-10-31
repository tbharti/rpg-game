package com.ramayan;

import java.io.IOException;
import java.sql.Connection;

import com.ramayan.model.Forest;
import com.ramayan.model.Player;
import com.ramayan.persistence.impl.HSQLDBConnection;
import com.ramayan.service.PlayerManagement;
import com.ramayan.service.UIservice;
import com.ramayan.service.impl.ConsoleBasedUI;
import com.ramayan.service.impl.UserManageCLI;

public final class Game {


	public void play() throws IOException {

		Connection db = HSQLDBConnection.getConnection();
		try {
			UIservice ui = new ConsoleBasedUI();
			ui.printStory();
			PlayerManagement usermanager = new UserManageCLI(db, ui);

			Player player = usermanager.selectPlayer();
			
			System.out.println("Let's go!!");
			Forest dungeon = Forest.newInstance();
			
			while (player.isAlive() && !player.isComplete() && !player.hasQuit()) {
				player.movePlayer(dungeon);
			}
			
			if (player.isAlive() && !player.hasQuit()) {
				usermanager.deletePlayer(player.getName());
				ui.displayFinishedGameMessage();
			} else if(!player.isAlive() && !player.hasQuit()) {
				usermanager.deletePlayer(player.getName());
				ui.displayLooseMessage();
			} else {
				ui.displayUnFinishedGameMessage();
			}
			
			usermanager.savePlayer(player);
			ui.exitMessage();
		} finally {
			HSQLDBConnection.closeConnection();
		}
	
	}

	public static void main(String[] args) throws IOException {
		Game game = new Game();
		game.play();
	}

}