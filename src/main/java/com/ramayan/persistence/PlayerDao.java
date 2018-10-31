package com.ramayan.persistence;

import java.util.List;

import com.ramayan.model.Player;

public interface PlayerDao {

	public List<Player> getUsers();

	public void persistUser(Player toSave);

	public void updateUser(Player player);

	public void deleteUser(String name);

}
