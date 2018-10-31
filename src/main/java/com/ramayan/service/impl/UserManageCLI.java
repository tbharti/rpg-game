package com.ramayan.service.impl;

import java.sql.Connection;
import java.util.List;

import com.ramayan.model.Player;
import com.ramayan.persistence.PlayerDao;
import com.ramayan.persistence.impl.PlayerDaoImpl;
import com.ramayan.service.PlayerManagement;
import com.ramayan.service.UIservice;

public class UserManageCLI implements PlayerManagement {

	PlayerDao userdao;
	UIservice ui;

	public UserManageCLI(Connection db, UIservice ui) {
		userdao = new PlayerDaoImpl(db);
		this.ui = ui;
	}

	public Player selectPlayer() {
		List<Player> users = userdao.getUsers();
		if (users != null && users.size() > 0) {
			ui.displayUsers(users);

			int selected = ui.readUserInputInt();
			if (selected > 0 && selected <= users.size()) {
				return users.get(selected - 1);
			} else if (selected == users.size() + 2) {
				deleteUser();
				return selectPlayer();
			} else if (selected == users.size() + 1) {
				return addPlayer();
			} else {
				ui.displayInvalidOptionMessage();
			}
		}
		return addPlayer();
	}

	private void deleteUser() {
		ui.confirmUserDeletion();
		String name = ui.readUserInputString();
		userdao.deleteUser(name);
	}
	

	public Player addPlayer() {
		ui.userIntroMessage();
		String name = ui.readUserInputString();
		Player selectedUser = new Player(name, ", you are a musclebound hulk intent on crushing all monsters in his way to reach Ravan and then defeat him to save your princess ", 40, 6, 20,
				10);
		ui.printUserDescription(selectedUser);
		userdao.persistUser(selectedUser);
		return selectedUser;
	}

	public void savePlayer(Player player) {
		userdao.updateUser(player);
	}

	@Override
	public void deletePlayer(String name) {
		userdao.deleteUser(name);
		
	}

}
