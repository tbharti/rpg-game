package com.ramayan.service;

import com.ramayan.model.Player;

public interface PlayerManagement {

	public Player selectPlayer();

	public Player addPlayer();

	public void savePlayer(Player player);

	public void deletePlayer(String name);
}